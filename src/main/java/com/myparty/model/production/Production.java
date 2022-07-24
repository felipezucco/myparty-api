package com.myparty.model.production;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myparty.annotations.DataConverterType;
import com.myparty.converter.production.ProductionConverter;
import com.myparty.enums.notification.NotificationTypeEnum;
import com.myparty.interfaces.notification.NotificationListener;
import com.myparty.model.Event;
import com.myparty.model.Financial;
import com.myparty.model.notification.Notification;
import com.myparty.model.notification.NotificationAttribute;
import com.myparty.model.organization.Organization;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "production")
@DataConverterType(ProductionConverter.class)
public class Production implements Serializable, NotificationListener {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Integer staffQuantity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "financial_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "production_financial_fk"))
    private Financial financial;

    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "production_event_fk"))
    @JsonIgnore
    private Event event;

    @JsonBackReference
    @OneToMany(mappedBy = "production", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductionCost> productionCosts;

    @Override
    public Notification postNotification(Long userId) {
        return Notification.builder()
                .notificationType(NotificationTypeEnum.PRODUCTION_CREATE)
                .attributes(
                        NotificationAttribute.builder().index(0).referenceTable("user_profile").referenceColumn("username").value(userId.toString()).build(),
                        NotificationAttribute.builder().index(1).referenceTable("production").referenceColumn("name").value(getName()).build(),
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
