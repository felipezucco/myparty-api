package com.myparty.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	@OneToMany
	@JoinTable(name = "role_authority", 
			joinColumns = @JoinColumn(name = "authority_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Authority> authorities;
}
