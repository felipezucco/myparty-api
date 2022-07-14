package com.myparty.model.production;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myparty.annotations.DataConverterType;
import com.myparty.converter.production.ProductionConverter;
import com.myparty.model.Event;
import com.myparty.model.Financial;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "production")
@DataConverterType(ProductionConverter.class)
public class Production implements Serializable {

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

}
