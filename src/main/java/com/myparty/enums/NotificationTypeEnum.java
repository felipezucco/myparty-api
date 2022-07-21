package com.myparty.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum NotificationTypeEnum {

	LOCAL_CREATE("local.post"),
	HOUSE_CREATE("house.post"),
	EVENT_CREATE("event.post"),
	TICKET_CREATE("ticket.post"),
	PRODUCTION_CREATE("production.post"),
	PROMOTION_CREATE("promotion.post"),
	VISUAL_CREATE("visual.post"),
	;

	private String code;

	public String getCode() {
		return code;
	}

}
