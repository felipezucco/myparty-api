package com.tendelfc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Local {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "local_id")
	private Long id;
	
	@Column(name = "cidade")
	private String city;
	
	@Column(length = 2)
	private String uf;
	
	@Column(name = "rua")
	private String aisle;
	
	@Column(name = "cep")
	private String code;
	
	@Column(name = "numero")
	private String number;
	
	@Column(name = "coordenada_x")
	private Double coordenateX;

	@Column(name = "coordenada_y")
	private Double coordenateY;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getAisle() {
		return aisle;
	}

	public void setAisle(String aisle) {
		this.aisle = aisle;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Double getCoordenateX() {
		return coordenateX;
	}

	public void setCoordenateX(Double coordenateX) {
		this.coordenateX = coordenateX;
	}

	public Double getCoordenateY() {
		return coordenateY;
	}

	public void setCoordenateY(Double coordenateY) {
		this.coordenateY = coordenateY;
	}

	@Override
	public String toString() {
		return "Local [city=" + city + ", uf=" + uf + ", aisle=" + aisle + ", code=" + code + ", number=" + number
				+ ", coordenateX=" + coordenateX + ", coordenateY=" + coordenateY + "]";
	}
	
	
}
