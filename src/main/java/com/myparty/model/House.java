package com.myparty.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Data	
public class House {

	@Id
	@GenericGenerator(strategy = "uuid",name = "system-uuid")
	@GeneratedValue(generator = "uuid")
	@Column(name = "house_id")
	private Long id;
	private String name;
	
	@JoinColumn(name = "local_id", referencedColumnName = "local_id")
	@OneToOne(fetch = FetchType.LAZY)
	private Local local;
	
//	@JoinColumn(name = "zone_id", referencedColumnName = "zone_id")
	@OneToMany(mappedBy = "house")
	private List<Zone> zones;
	
}
