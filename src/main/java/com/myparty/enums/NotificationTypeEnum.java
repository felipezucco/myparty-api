package com.myparty.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum NotificationTypeEnum {

	LOCAL_CREATE("local.post"),
	HOUSE_CREATE("house.post"),
	EVENT_CREATE("event.post"),
	TICKET_CREATE("ticket.post"),
	;

	private String code;

	public String getCode() {
		return code;
	}

}
