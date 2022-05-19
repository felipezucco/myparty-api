package com.myparty.model;

import com.myparty.annotations.DataConverterType;
import com.myparty.converter.OrganizerConverter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import com.myparty.enums.RoleEnum;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;

import lombok.Data;

@Data
@Entity
@DataConverterType(OrganizerConverter.class)
public class Organizer {

    @Id
    @GenericGenerator(strategy = "uuid", name = "system-uuid")
    @GeneratedValue(generator = "uuid")
    private Long id;

    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "organizer_user_fk"))
    @OneToOne
    private UserProfile user;

    @Column(name = "role_number")
    private RoleEnum role = RoleEnum.USER;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "organization_id", foreignKey = @ForeignKey(name = "organizer_organization_fk"))
    private Organization organization;
}
