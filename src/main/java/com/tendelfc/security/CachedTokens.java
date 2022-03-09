package com.tendelfc.security;

import java.util.HashSet;
import java.util.Set;

public class CachedTokens {

	private static Set<String> tokens = new HashSet<String>();
	
	public static String validyToken(String token) {
		return tokens.stream().filter(t -> t.equals(token)).findFirst().orElseThrow(() -> new RuntimeException("Token inexistente"));
	}
	
	public static void add(String token) {
		tokens.add(token);
	}
	
	public static void remove(String token) {
		tokens.remove(token);
	}
	
}
