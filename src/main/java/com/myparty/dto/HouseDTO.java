package com.myparty.dto;

import java.util.List;

import lombok.Data;

@Data
public class HouseDTO {

	private Long id;
	private String name;
	private LocalDTO local; 
	private List<ZoneDTO> zones;
	
}
