package com.myparty.model;

import com.myparty.annotations.DataConverterType;
import com.myparty.converter.VisualConverter;
import com.myparty.enums.notification.NotificationTypeEnum;
import com.myparty.interfaces.notification.NotificationListener;
import com.myparty.model.notification.Notification;
import com.myparty.model.notification.NotificationAttribute;
import com.myparty.model.organization.Organization;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "visual")
@DataConverterType(VisualConverter.class)
public class Visual implements NotificationListener {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private String channel;
    private String type;

    @Column(name = "release_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date releaseDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "financial_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "visual_financial_fk"))
    private Financial financial;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "visual_event_fk"))
    private Event event;

    @Override
    public Notification postNotification(Long userId) {
        return Notification.builder()
                .notificationType(NotificationTypeEnum.VISUAL_CREATE)
                .attributes(
                        NotificationAttribute.builder().index(0).referenceTable("user_profile").referenceColumn("username").value(userId.toString()).build(),
                        NotificationAttribute.builder().index(1).referenceTable("visual").referenceColumn("name").value(getName()).build(),
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
