package com.myparty.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Organization {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "organization_id")
	private Long id;

	private String name;

	@OneToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "account_id", referencedColumnName = "account_id", nullable = false, foreignKey = @ForeignKey(name = "organization_account_fk"))
	private Account account;

	@OneToMany(mappedBy = "organization", targetEntity = Organizer.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Organizer> organizers;
}
