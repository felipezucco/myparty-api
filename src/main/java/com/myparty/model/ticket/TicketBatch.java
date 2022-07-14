package com.myparty.model.ticket;

import com.myparty.annotations.DataConverterType;
import com.myparty.converter.ticket.TicketBatchConverter;

import javax.persistence.*;

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

    @Column(name = "first_number")
    private Integer firstNumber;

    @JoinTable(name = "ticket_ticket_batch",
            joinColumns = @JoinColumn(name = "ticket_batch_id", foreignKey = @ForeignKey(name = "ticket_ticket_batch_fk")),
            inverseJoinColumns = @JoinColumn(name = "ticket_id", foreignKey = @ForeignKey(name = "ticket_batch_ticket_fk")))
    @ManyToOne(fetch = FetchType.LAZY)
    private Ticket ticket;

}
