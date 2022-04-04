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
@Table(name = "ticket_bracelet")
@Data
public class TicketBracelet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ticket_bracelet_id")
	private Long id;
	
	private Integer number;
	
	@JoinColumn(name = "ticket_batch_id", referencedColumnName = "ticket_batch_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private TicketBatch ticketBatch;
	
}
