package com.myparty.dto;

import java.io.Serializable;
import java.util.List;

import com.myparty.annotations.DataConverterType;
import com.myparty.converter.notification.NotificationConverter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DataConverterType(NotificationConverter.class)
public class GetNotification implements Serializable {

	private Long id;
	private String type;
	private String message;
	private String organization;
	private String date;
	private boolean visualized;
	private String user;
	private List<String> attributes;
	private List<String> instructions;

}
