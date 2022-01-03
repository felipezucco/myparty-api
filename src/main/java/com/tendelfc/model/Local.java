package com.tendelfc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
public class Local {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "local_id")
	private Long id;
	
	@Column(name = "cidade")
	@NotBlank(message = "Campo CIDADE não pode ser vazio.")
	private String city;
	
	@Column(length = 2)
	private String uf;
	
	@Column(name = "rua")
	private String aisle;
	
	@Column(name = "cep")
	private String code;
	
	@Column(name = "numero")
	private Integer number;
	
	@Column(name = "coordenada_x")
	private Double coordenateX;

	@Column(name = "coordenada_y")
	private Double coordenateY;
	
	
}
