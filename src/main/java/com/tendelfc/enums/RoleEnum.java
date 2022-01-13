package com.tendelfc.enums;

import java.util.stream.Stream;

public enum RoleEnum {
	NO_ROLE(0), ROLE_ADMIN(1), ROLE_MANAGER(2), ROLE_USER(3);
	
	private Integer id;

	private RoleEnum(Integer id) {
		this.id = id;
	}

	public static RoleEnum getRoleEnumById(Integer id) {
		if (id == null) return NO_ROLE;
		return Stream.of(RoleEnum.values()).filter(role -> role.getId().equals(id)).findFirst().orElseThrow(IllegalArgumentException::new);
	}
	
	public Integer getId() {
		return id;
	}	
	
}
