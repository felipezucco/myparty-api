package com.myparty.dto;

import java.io.Serializable;
import java.util.List;

import com.myparty.annotations.DataConverterType;
import com.myparty.converter.notification.NotificationSentConverterOld;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DataConverterType(NotificationSentConverterOld.class)
public class NotificationDTO implements Serializable {
	private static final long serialVersionUID = -351028616460087093L;

	private Long id;
	private String message;
	private String organization;
	private String date;
	private boolean visualized;
	private String user;
	private List<String> attributes;

}
