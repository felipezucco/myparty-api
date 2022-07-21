package com.myparty.model.notification;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.Check;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
	@JsonManagedReference
	@ToString.Exclude
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

}
