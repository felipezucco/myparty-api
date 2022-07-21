package com.myparty.model;

import com.myparty.annotations.DataConverterType;
import com.myparty.converter.PromotionConverter;
import com.myparty.enums.NotificationTypeEnum;
import com.myparty.interfaces.notification.NotificationListener;
import com.myparty.model.action.Action;
import com.myparty.model.notification.Notification;
import com.myparty.model.notification.NotificationAttribute;
import com.myparty.model.organization.Organization;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "promotion")
@DataConverterType(PromotionConverter.class)
public class Promotion implements NotificationListener {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;

    @Column(name = "start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Column(name = "end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "promotion_event_id"))
    private Event event;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "action_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "promotion_action_fk"))
    private Action action;

    @Override
    public Notification postNotification(Long userId) {
        return Notification.builder()
                .notificationType(NotificationTypeEnum.PROMOTION_CREATE)
                .attributes(
                        NotificationAttribute.builder().index(0).referenceTable("user_profile").referenceColumn("username").value(userId.toString()).build(),
                        NotificationAttribute.builder().index(1).referenceTable("promotion").referenceColumn("name").value(getName()).build(),
                        NotificationAttribute.builder().index(2).referenceTable("event").referenceColumn("name").value(getEvent().getName()).build()
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
        return getEvent().getOrganization();
    }
}
