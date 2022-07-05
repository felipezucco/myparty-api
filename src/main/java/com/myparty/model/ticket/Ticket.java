package com.myparty.model.ticket;

import com.myparty.annotations.DataConverterType;
import com.myparty.converter.TicketConverter;
import com.myparty.model.Event;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
@DataConverterType(TicketConverter.class)
public class Ticket implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @JoinColumn(name = "event_id", foreignKey = @ForeignKey(name = "ticket_event_fk"))
    @ManyToOne(fetch = FetchType.LAZY)
    private Event event;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "ticket_ticket_batch",
            inverseJoinColumns = @JoinColumn(name = "ticket_batch_id", foreignKey = @ForeignKey(name = "ticket_ticket_batch_fk")),
            joinColumns = @JoinColumn(name = "ticket_id", foreignKey = @ForeignKey(name = "ticket_batch_ticket_fk")))
    private List<TicketBatch> batchs;
    
}
