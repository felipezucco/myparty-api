package com.myparty.utils;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64Decoder {

	public static String decode(String code) {
		return new String(Base64.getDecoder().decode(URLDecoder.decode(code, StandardCharsets.UTF_8)));
	};
}
