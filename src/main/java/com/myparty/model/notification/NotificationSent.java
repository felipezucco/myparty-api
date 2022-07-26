package com.myparty.model.notification;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.myparty.annotations.DataConverterType;
import com.myparty.converter.notification.NotificationConverter;
import com.myparty.model.UserProfile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notification_sent")
@DataConverterType(NotificationConverter.class)
public class NotificationSent {

	@Id
	@GeneratedValue
	private Long id;

	@Version
	private Integer version;

	private boolean visualized;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "notification_id", foreignKey = @ForeignKey(name = "notification_sent_notification_fk"))
	@JsonManagedReference
	@ToString.Exclude
	private Notification notification;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "notification_sent_user_fk"))
	private UserProfile user;

}
