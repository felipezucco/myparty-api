package com.myparty.model.notification;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.myparty.annotations.DataConverterType;
import com.myparty.converter.NotificationTypeConverter;
import com.myparty.enums.NotificationTypeEnum;
import com.myparty.model.organization.Organization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "notification")
@AllArgsConstructor
@NoArgsConstructor
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
	@JoinColumn(name = "organization_id", foreignKey = @ForeignKey(name = "notification_organization_fk"))
	private Organization organization;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@OneToMany(mappedBy = "notification", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonBackReference
	private List<NotificationSent> sent;

	@OneToMany(mappedBy = "notification", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonBackReference
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
			this.setAttributes(new ArrayList<>());
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
