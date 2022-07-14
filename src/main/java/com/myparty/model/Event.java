package com.myparty.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myparty.annotations.DataConverterType;
import com.myparty.converter.EventConverter;
import com.myparty.enums.NotificationTypeEnum;
import com.myparty.interfaces.notification.NotificationListener;
import com.myparty.model.house.House;

import com.myparty.model.notification.Notification;
import com.myparty.model.organization.Organization;
import lombok.Data;

@Data
@Entity
@DataConverterType(EventConverter.class)
public class Event implements NotificationListener {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@JoinColumn(name = "house_id")
	@OneToOne(fetch = FetchType.LAZY)
	private House house;

	@Override
	public Notification postNotification(Long userId) {
		Notification notification = Notification.builder()
				.setNotificationType(NotificationTypeEnum.EVENT_CREATE)
				.addAttribute(0,"user_profile", "username", userId.toString())
				.addAttribute(1,"event", "name", getName());
		return notification;
	}

	@Override
	public void afterUpdate() {

	}

	@Override
	public void afterDelete() {

	}

	@Override
	@JsonIgnore
	public Organization getOrganization() {
		return getHouse().getLocal().getOrganization();
	}
}
