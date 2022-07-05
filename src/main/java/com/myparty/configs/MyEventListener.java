package com.myparty.configs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.transaction.Synchronization;

import com.myparty.model.Authority;
import com.myparty.model.notification.NotificationAttribute;
import org.hibernate.Session;
import org.hibernate.action.internal.EntityInsertAction;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.*;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.myparty.controller.NotificationController;
import com.myparty.converter.NotificationConverter;
import com.myparty.dto.NotificationDTO;
import com.myparty.interfaces.notification.NotificationListener;
import com.myparty.manager.OrganizationTools;
import com.myparty.manager.notification.NotificationTools;
import com.myparty.model.notification.Notification;
import com.myparty.service.NotificationService;

@Component
public class MyEventListener implements PostInsertEventListener, PostUpdateEventListener, PreInsertEventListener {
	private static final long serialVersionUID = 1L;

	private EntityManagerFactory entityManagerFactory;
	private NotificationController notificationController;
	private ExecutorService nonBlockingService = Executors.newCachedThreadPool();
	private NotificationTools notificationTools;
	private NotificationConverter notificationConverter;
	private NotificationService notificationService;

	private SessionFactoryImpl sessionFactory;

	public MyEventListener(EntityManagerFactory entityManagerFactory, NotificationController notificationController,
			NotificationTools notificationTools, NotificationConverter notificationConverter, NotificationService notificationService) {
		super();
		this.entityManagerFactory = entityManagerFactory;
		this.notificationController = notificationController;
		this.notificationTools = notificationTools;
		this.notificationConverter = notificationConverter;
		this.notificationService = notificationService;
	}

	@PostConstruct
	private void init() {
		sessionFactory = entityManagerFactory.unwrap(SessionFactoryImpl.class);
		EventListenerRegistry registry = sessionFactory.getServiceRegistry().getService(EventListenerRegistry.class);
		registry.getEventListenerGroup(EventType.POST_INSERT).appendListener(this);
	}

	@Override
	public void onPostInsert(PostInsertEvent postInsertEvent) {
		Object entity = postInsertEvent.getEntity();

		postInsertEvent.getSession().accessTransaction().registerSynchronization(new Synchronization() {
			@Override
			public void beforeCompletion() {

			}

			@Override
			public void afterCompletion(int status) {


				if (entity instanceof NotificationListener) {
					NotificationListener notifier = (NotificationListener) entity;
					Notification notification = notificationTools.buildNotification(NotificationTools.POST, notifier);

					notificationTools.buildOrganizationNotificationSent(notifier, notification);

					Session a = sessionFactory.openSession();
					a.beginTransaction();
					a.persist(notification);
					a.getTransaction().commit();
					a.disconnect();
					//a.

					NotificationDTO convert = notificationConverter.convert(notification);
					List<String> organizers = OrganizationTools.getOrganizersUsername(notifier.getOrganization());
					organizers.forEach(organizer -> {

								notificationController.getSses().computeIfPresent(organizer,
										(String k, SseEmitter v) -> {
											//nonBlockingService.execute(() -> {
											try {
												v.send(SseEmitter.event().data(convert));

											} catch (IOException e) {
												Logger.getGlobal().log(Level.SEVERE, organizer, e);
											}

											//});
											return v;
										});
							}
					);
				}
			}
		});
		/*if (entity instanceof NotificationListener) {
			NotificationListener notifier = (NotificationListener) entity;
			Notification notification = notificationTools.buildNotification(NotificationTools.POST, notifier);

			notificationTools.buildOrganizationNotificationSent(notifier, notification);
			//notificationService.persistNotification(notification);

			//postInsertEvent.getSession().save(notification);
			//postInsertEvent.getSession().save(notifier);


			Authority authority = new Authority();
			authority.setName("uma bola");


			NotificationDTO convert = notificationConverter.convert(notification);
			List<String> organizers = OrganizationTools.getOrganizersUsername(notifier.getOrganization());
			organizers.forEach(organizer -> {

				notificationController.getSses().computeIfPresent(organizer,
					(String k, SseEmitter v) -> {
						//nonBlockingService.execute(() -> {
							try {
								v.send(SseEmitter.event().data(convert));

							} catch (IOException e) {
								Logger.getGlobal().log(Level.SEVERE, organizer, e);
							}

						//});
						return v;
					});
				}
			);
		}*/

	}

	@Override
	public boolean requiresPostCommitHandling(EntityPersister persister) {
		return PostInsertEventListener.super.requiresPostCommitHandling(persister);
	}

	@Override
	public boolean requiresPostCommitHanding(EntityPersister persister) {
		return false;
	}

	@Override
	public void onPostUpdate(PostUpdateEvent event) {
		//event.ge

	}

	@Override
	public boolean onPreInsert(PreInsertEvent event) {
		return false;
	}
}