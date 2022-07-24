package com.myparty.model.house;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.myparty.enums.notification.NotificationTypeEnum;
import com.myparty.interfaces.notification.NotificationListener;
import com.myparty.model.notification.Notification;
import com.myparty.model.notification.NotificationAttribute;
import com.myparty.model.organization.Organization;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import com.myparty.annotations.DataConverterType;
import com.myparty.converter.HouseConverter;
import com.myparty.model.Local;

import lombok.Data;

@Entity
@Data
@DataConverterType(HouseConverter.class)
public class House implements NotificationListener {

	@Id
	@GenericGenerator(strategy = "uuid", name = "system-uuid")
	@GeneratedValue(generator = "uuid")
	private Long id;
	private String name;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "local_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "house_local_fk"))
	private Local local;

	@OneToMany(mappedBy = "house", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@ToString.Exclude
	@JsonBackReference
	private List<Zone> zones;

	@Override
	public Notification postNotification(Long userId) {
		return Notification.builder()
				.notificationType(NotificationTypeEnum.HOUSE_CREATE)
				.attributes(
						NotificationAttribute.builder().index(0).referenceTable("user_profile").referenceColumn("username").value(userId.toString()).build(),
						NotificationAttribute.builder().index(1).referenceTable("house").referenceColumn("name").value(getName()).build()
				);
	}

	@Override
	public void afterUpdate() {

	}

	@Override
	public void afterDelete() {

	}

	@Override
	public Organization getOrganization() {
		return getLocal().getOrganization();
	}
}
