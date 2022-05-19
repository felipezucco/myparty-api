package com.myparty.model;

import com.myparty.annotations.DataConverterType;
import com.myparty.converter.TicketBatchConverter;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "ticket_batch")
@Data
@DataConverterType(TicketBatchConverter.class)
public class TicketBatch {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer quantity;
    private String name;
    private Double price;
    private Integer firstNumber;

    @JoinTable(name = "ticket_ticket_batch",
            joinColumns = @JoinColumn(name = "ticket_batch_id", foreignKey = @ForeignKey(name = "ticket_ticket_batch_fk")),
            inverseJoinColumns = @JoinColumn(name = "ticket_id", foreignKey = @ForeignKey(name = "ticket_batch_ticket_fk")))
    @ManyToOne(fetch = FetchType.LAZY)
    private Ticket ticket;

}
