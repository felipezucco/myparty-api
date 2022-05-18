package com.myparty.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import com.myparty.annotations.DataConverterType;
import com.myparty.converter.LocalConverter;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

@Data
@Entity
@DataConverterType(LocalConverter.class)
public class Local {

    @Id
    @GenericGenerator(strategy = "uuid", name = "system-uuid")
    @GeneratedValue(generator = "uuid")
    private Long id;
    private String city;

    @Column(length = 2)
    private String state;
    private String aisle;
    private String code;
    private String block;
    private String complement;
    private Integer number;
    private Double coordenateX;
    private Double coordenateY;
    
    @ManyToOne
    @JoinTable(name = "local_organization", 
            joinColumns = @JoinColumn(name = "local_id"), foreignKey = @ForeignKey(name = "local_organization_fk"),
            inverseJoinColumns = @JoinColumn(name = "organization_id"), inverseForeignKey = @ForeignKey(name = "organization_local_fk"))
    private Organization organization;

}
