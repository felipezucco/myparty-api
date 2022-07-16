package com.myparty.model.house;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.myparty.enums.NotificationTypeEnum;
import com.myparty.interfaces.notification.NotificationListener;
import com.myparty.model.notification.Notification;
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
		Notification notification = Notification.builder()
				.setNotificationType(NotificationTypeEnum.HOUSE_CREATE)
				.addAttribute(0,"user_profile", "username", userId.toString())
				.addAttribute(1,"house", "name", getName());
		return notification;
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
