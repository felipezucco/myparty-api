package com.myparty.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
		
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	@JoinColumn(name = "house_id")
	@OneToOne(fetch = FetchType.LAZY)
	private House house;
	
	@ManyToOne
	@JoinTable(name = "event_organization", 
			joinColumns = @JoinColumn(name = "organization_id"),
			inverseJoinColumns = @JoinColumn(name = "event_id"))
	private Organization organization;
	
}
