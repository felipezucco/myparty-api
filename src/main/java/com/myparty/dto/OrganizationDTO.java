package com.myparty.dto;

import java.util.List;

import lombok.Data;

@Data
public class OrganizationDTO {

	private Long id;
	private String name;
	private List<OrganizerDTO> organizers;
	
}
