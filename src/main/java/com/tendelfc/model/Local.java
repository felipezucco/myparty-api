package com.tendelfc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Local {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "local_id")
	private Long id;

	private String city;

	@Column(length = 2)
	private String state;

	private String aisle;

	private String code;

	private Integer number;

	private Double coordenateX;
	
	private Double coordenateY;

}
