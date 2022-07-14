package com.myparty.model;


import com.myparty.model.organization.Organizer;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "action")
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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "action_organizer",
        joinColumns = @JoinColumn(name = "action_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "action_organizer_fk")),
        inverseJoinColumns = @JoinColumn(name = "organizer_id", referencedColumnName = "id"), foreignKey = @ForeignKey(name = "organizer_action_fk"))
    private List<Organizer> organizers;

}
