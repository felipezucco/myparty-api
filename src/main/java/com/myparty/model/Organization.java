package com.myparty.model;

import com.myparty.annotations.DTO;
import com.myparty.dto.OrganizationDTO;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
@DTO(OrganizationDTO.class)
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "organization_user_fk"))
    private UserProfile user;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    private List<Organizer> organizers;
        
}
