package com.myparty.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "ticket_batch")
@Data
public class TicketBatch {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ticket_batch_id")
	private Long id;
		
	private Integer number;
	
	private Double price;
	
	@JoinColumn(name = "ticket_id", referencedColumnName = "ticket_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Ticket ticket;
	
}
