package com.myparty.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum NotificationTypeEnum {

	LOCAL_CREATE("local.post"), HOUSE_CREATE("house.post");

	private String code;

	public String getCode() {
		return code;
	}

}
