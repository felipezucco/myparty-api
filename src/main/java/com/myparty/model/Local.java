package com.myparty.model;

import javax.persistence.*;

import com.myparty.model.notification.NotificationAttribute;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import com.myparty.annotations.DataConverterType;
import com.myparty.converter.LocalConverter;
import com.myparty.enums.notification.NotificationTypeEnum;
import com.myparty.interfaces.notification.NotificationListener;
import com.myparty.model.notification.Notification;
import com.myparty.model.organization.Organization;

import lombok.Data;

@Data
@Entity
@DataConverterType(LocalConverter.class)
public class Local implements NotificationListener {

	@Id
	@GenericGenerator(strategy = "uuid", name = "system-uuid")
	@GeneratedValue(generator = "uuid")
	private Long id;
	private String city;

	@Version
	private Integer version;

	@Column(length = 2)
	private String state;
	private String aisle;
	private String code;
	private String block;
	private String complement;
	private Integer number;
	private Double coordenateX;
	private Double coordenateY;

	@ManyToOne
	@JoinTable(name = "local_organization",
	joinColumns = @JoinColumn(name = "local_id"), foreignKey = @ForeignKey(name = "local_organization_fk"),
	inverseJoinColumns = @JoinColumn(name = "organization_id"), inverseForeignKey = @ForeignKey(name = "organization_local_fk"))
	@ToString.Exclude
	private Organization organization;

	@Override
	public Notification postNotification(Long userId) {
		 return Notification.builder()
				.notificationType(NotificationTypeEnum.LOCAL_CREATE)
				.attributes(
						NotificationAttribute.builder().index(0).referenceTable("user_profile").referenceColumn("username").value(userId.toString()).build()
				);
	}

	@Override
	public void afterUpdate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterDelete() {
		// TODO Auto-generated method stub

	}

}
