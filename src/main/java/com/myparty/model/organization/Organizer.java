package com.myparty.model.organization;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.myparty.annotations.DataConverterType;
import com.myparty.converter.organization.OrganizerConverter;

import javax.persistence.*;

import com.myparty.model.Action;

import com.myparty.enums.RoleEnum;
import com.myparty.model.UserProfile;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Entity
@DataConverterType(OrganizerConverter.class)
@Table(name = "organizer")
public class Organizer {

    @Id
    @GeneratedValue
    private Long id;

    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "organizer_user_fk"))
    @OneToOne
    private UserProfile user;

    @Column(name = "role_number")
    private RoleEnum role = RoleEnum.USER;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "organization_member",
            inverseJoinColumns = @JoinColumn(name = "organization_id", foreignKey = @ForeignKey(name = "organization_organizer_fk")),
            joinColumns = @JoinColumn(name = "organizer_id", foreignKey = @ForeignKey(name = "organizer_organization_fk")))
    @ToString.Exclude
    @JsonManagedReference
    private Organization organization;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "action_organizer",
            joinColumns = @JoinColumn(name = "organizer_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "action_organizer_fk")),
            inverseJoinColumns = @JoinColumn(name = "action_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "organizer_action_fk")))
    private List<Action> actions;

}
