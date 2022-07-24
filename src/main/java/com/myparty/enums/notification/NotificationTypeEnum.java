package com.myparty.enums.notification;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum NotificationTypeEnum {

	LOCAL_CREATE("local.post"),
	HOUSE_CREATE("house.post"),
	EVENT_CREATE("event.post"),
	TICKET_CREATE("ticket.post"),
	PRODUCTION_CREATE("production.post"),
	PROMOTION_CREATE("promotion.post"),
	VISUAL_CREATE("visual.post"),
	ORGANIZATION_CREATE("organization.post", NotificationEventEnum.RELOAD_ORGANIZATIONS),
	;

	private String code;
	private NotificationEventEnum[] events;

	NotificationTypeEnum(String code, NotificationEventEnum... events) {
		this.code = code;
		this.events = events;
	}

	public String getCode() {
		return code;
	}

	public List<String> getEvents() {
		return Arrays.stream(events).map(NotificationEventEnum::name).collect(Collectors.toList());
	}
}
