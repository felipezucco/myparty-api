package com.myparty.model.notification;

import javax.persistence.*;

import org.hibernate.annotations.Check;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notification_attribute")
public class NotificationAttribute {

	@Id
	@GeneratedValue
	private Long id;

	@Version
	private int version;

	@JoinColumn(name = "notification_id", foreignKey = @ForeignKey(name = "notification_attribute_notification_fk"))
    @ManyToOne(fetch = FetchType.LAZY)
	private Notification notification;

	@Column(name = "index")
	@Check(constraints = "index >= 0")
	private Integer index;

	@Column(name = "reference_table")
	private String referenceTable;

	@Column(name = "reference_column")
	private String referenceColumn;

	@Column(name = "reference_value")
	private String value;

	public static NotificationAttribute builder() {
		return new NotificationAttribute();
	}

	public static NotificationAttribute builder(Notification notification, Integer index, String table, String column, String value) {
		NotificationAttribute builder = NotificationAttribute.builder();
		builder.setIndex(index);
		builder.setNotification(notification);
		builder.setReferenceTable(table);
		builder.setReferenceColumn(column);
		builder.setValue(value);
		return builder;
	}

}
