package com.tendelfc.dto;

import lombok.Data;

@Data
public class EventDTO {
	
	private Long id;
	private String name;
	private String date;
	private LocalDTO local;
		
}
