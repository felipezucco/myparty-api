package com.tendelfc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ingresso_pulseira")
public class TicketBracelet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ingresso_pulseira_id")
	private Long id;
	
	@Column(name = "numero")
	private Integer number;
	
	@JoinColumn(name = "lote_ingresso_id", referencedColumnName = "lote_ingresso_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private TicketBatch ticketBatch;
	
}
