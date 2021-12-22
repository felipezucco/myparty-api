package com.tendelfc.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "evento")
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "evento_id")
	private Long id;
	
	@Column(name = "nome")
	private String name;
	
	@Column(name = "data")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	@JoinColumn(name = "local_id", referencedColumnName = "local_id")
	@OneToOne(fetch = FetchType.LAZY)
	private Local local;
	
}
