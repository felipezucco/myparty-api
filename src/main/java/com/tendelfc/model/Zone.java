package com.tendelfc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Zone {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "zone_id")
	private Long id;
	
	private String name;
	
	private Double size;
	
	private String color;
	
	@JoinColumn(name = "house_id", referencedColumnName = "house_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private House house;
	
}
