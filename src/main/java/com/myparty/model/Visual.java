package com.myparty.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "visual")
public class Visual {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @Column(name = "release_date")
    private Date releaseDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "financial_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "visual_financial_fk"))
    private Financial financial;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "visual_event_fk"))
    private Event event;

}
