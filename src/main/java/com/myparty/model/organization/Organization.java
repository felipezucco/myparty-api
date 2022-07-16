package com.myparty.model.organization;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.myparty.annotations.DataConverterType;
import com.myparty.converter.BooleanConverterType;
import com.myparty.converter.organization.OrganizationConverter;

import lombok.Data;
import org.hibernate.annotations.Type;

@Data
@Entity
@DataConverterType(OrganizationConverter.class)
public class Organization {

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

}
