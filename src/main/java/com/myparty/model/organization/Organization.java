package com.myparty.model.organization;

import java.util.List;

import javax.persistence.*;

import com.myparty.annotations.DataConverterType;
import com.myparty.converter.BooleanConverterType;
import com.myparty.converter.organization.OrganizationConverter;

import com.myparty.enums.notification.NotificationTypeEnum;
import com.myparty.interfaces.notification.NotificationListener;
import com.myparty.model.notification.Notification;
import com.myparty.model.notification.NotificationAttribute;
import lombok.Data;

@Data
@Entity
@DataConverterType(OrganizationConverter.class)
public class Organization implements NotificationListener {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @Convert(converter = BooleanConverterType.class)
    private Boolean favorite;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "organization_member",
            joinColumns = @JoinColumn(name = "organization_id", foreignKey = @ForeignKey(name = "organization_organizer_fk")),
            inverseJoinColumns = @JoinColumn(name = "organizer_id", foreignKey = @ForeignKey(name = "organizer_organization_fk")))
    private List<Organizer> organizers;

    @Override
    public Notification postNotification(Long userId) {
        return Notification.builder()
                .notificationType(NotificationTypeEnum.ORGANIZATION_CREATE)
                .attributes(
                        NotificationAttribute.builder().index(0).referenceTable("user_profile").referenceColumn("username").value(userId.toString()).build(),
                        NotificationAttribute.builder().index(1).referenceTable("organization").referenceColumn("name").value(userId.toString()).build()
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
        return this;
    }
}
