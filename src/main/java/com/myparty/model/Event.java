package com.myparty.model;

import java.util.Arrays;
import java.util.Collections;
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
import com.myparty.model.notification.NotificationAttribute;
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
		return Notification.builder()
				.notificationType(NotificationTypeEnum.EVENT_CREATE)
				.attributes(
						NotificationAttribute.builder().index(0).referenceTable("user_profile").referenceColumn("username").value(userId.toString()).build(),
						NotificationAttribute.builder().index(1).referenceTable("event").referenceColumn("name").value(getName()).build()
				);
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
