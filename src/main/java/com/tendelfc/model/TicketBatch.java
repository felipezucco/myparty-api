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
@Table(name = "lote_ingresso")
public class TicketBatch {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "lote_ingresso_id")
	private Long id;
		
	@Column(name = "numero")
	private Integer number;
	
	@Column(name = "preco")
	private Double price;
	
	@JoinColumn(name = "ingresso_id", referencedColumnName = "ingresso_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Ticket ticket;
	
	
	
	
}
