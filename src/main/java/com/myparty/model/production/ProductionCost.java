package com.myparty.model.production;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.myparty.annotations.DataConverterType;
import com.myparty.converter.production.ProductionCostConverter;
import com.myparty.model.Financial;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name = "production_cost")
@DataConverterType(value = ProductionCostConverter.class)
public class ProductionCost {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Double quantity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "financial_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "production_cost_financial_fk"))
    private Financial financial;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "production_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "production_cost_production_fk"))
    @JsonManagedReference
    @ToString.Exclude
    private Production production;

}
