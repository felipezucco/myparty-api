package com.tendelfc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Authority {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "authority_id")
	private Long id;
	
	private String authority;
	
}