package com.myparty.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "ticket_bracelet")
@Data
public class TicketBracelet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer number;

    @JoinColumn(name = "ticket_batch_id", foreignKey = @ForeignKey(name = "ticket_brac_ticket_bat_fk"))
    @ManyToOne(fetch = FetchType.LAZY)
    private TicketBatch ticketBatch;

}
