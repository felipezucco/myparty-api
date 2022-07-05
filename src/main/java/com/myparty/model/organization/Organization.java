package com.myparty.model.organization;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import com.myparty.annotations.DataConverterType;
import com.myparty.converter.OrganizationConverter;

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

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "organization_member",
            joinColumns = @JoinColumn(name = "organization_id", foreignKey = @ForeignKey(name = "organization_organizer_fk")),
            inverseJoinColumns = @JoinColumn(name = "organizer_id", foreignKey = @ForeignKey(name = "organizer_organization_fk")))
    private List<Organizer> organizers;

}
