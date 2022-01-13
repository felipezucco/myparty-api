package com.tendelfc.dto;

import lombok.Data;

@Data
public class AccountDTO {

	private Long id;
	private String username;
	private String password;
	private String email;
	private String name;
	private Integer role;
	
}
