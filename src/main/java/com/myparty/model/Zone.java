package com.myparty.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import com.myparty.annotations.DataConverterType;
import com.myparty.converter.ZoneConverter;

@Data
@Entity
@DataConverterType(ZoneConverter.class)
public class Zone {

    @Id
    @GenericGenerator(strategy = "uuid", name = "system-uuid")
    @GeneratedValue(generator = "uuid")
    private Long id;
    private String name;
    private Double size;

    @JoinColumn(name = "house_id", foreignKey = @ForeignKey(name = "zone_house_fk"))
    @ManyToOne(fetch = FetchType.LAZY)
    private House house;

}
