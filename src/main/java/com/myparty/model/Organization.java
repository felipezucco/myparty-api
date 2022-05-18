package com.myparty.model;

import com.myparty.annotations.DataConverterType;
import com.myparty.converter.OrganizationConverter;
import java.awt.datatransfer.FlavorListener;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
@DataConverterType(OrganizationConverter.class)
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private boolean favorite;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    private List<Organizer> organizers;
        
}
