package com.myparty.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Organization {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "organization_id")
	private Long id;
	
	private String name;
	
	@OneToMany(mappedBy = "organization", targetEntity = Organizer.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Organizer> organizers;
}
