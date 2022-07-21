package com.myparty.model.action;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.myparty.annotations.DataConverterType;
import com.myparty.converter.action.ActionConverter;
import com.myparty.converter.action.ActionLinkConverter;
import com.myparty.model.Event;
import com.myparty.model.organization.Organizer;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "action")
@DataConverterType(ActionConverter.class)
public class Action {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @Column(name = "start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Column(name = "end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "action_event_fk"))
    private Event event;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "action_organizer",
        joinColumns = @JoinColumn(name = "action_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "action_organizer_fk")),
        inverseJoinColumns = @JoinColumn(name = "organizer_id", referencedColumnName = "id"), foreignKey = @ForeignKey(name = "organizer_action_fk"))
    private List<Organizer> organizers;

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "action", fetch = FetchType.LAZY)
    private ActionLink actionLinks;

}
