package com.myparty.model.notification;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;

import com.myparty.annotations.DataConverterType;
import com.myparty.converter.NotificationConverter;
import com.myparty.converter.NotificationTypeConverter;
import com.myparty.enums.NotificationTypeEnum;
import com.myparty.model.UserProfile;
import com.myparty.model.organization.Organization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "notification")
@AllArgsConstructor
@NoArgsConstructor
@DataConverterType(value = NotificationConverter.class)
public class Notification {

	@Id
	@GeneratedValue
	private Long id;

	@Version
	private int version;

	@Convert(converter = NotificationTypeConverter.class)
	@Column(name = "notification")
	private NotificationTypeEnum notificationType;

	@ManyToOne
	@JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "notification_user_fk"))
	private UserProfile user;

	@ManyToOne
	@JoinColumn(name = "organization_id", foreignKey = @ForeignKey(name = "notification_organization_fk"))
	private Organization organization;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@OneToMany(mappedBy = "notification", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<NotificationSent> sent;

	@OneToMany(mappedBy = "notification", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<NotificationAttribute> attributes;


	public static Notification builder() {
		return new Notification();
	}

	public Notification setNotificationType(NotificationTypeEnum typeEnum) {
		notificationType = typeEnum;
		return this;
	}

	public Notification addAttribute(Integer index, String table, String column, String value) {
		if (this.getAttributes() == null) {
			this.setAttributes(new ArrayList<NotificationAttribute>());
		}

		NotificationAttribute attribute = NotificationAttribute.builder(this, index, table, column, value);
		this.getAttributes().add(attribute);
		return this;
	}

	public Notification addSent(NotificationSent sent) {
		if (getSent() == null) {
			setSent(new ArrayList<>());
		}

		getSent().add(sent);
		return this;
	}

}
