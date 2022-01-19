package com.tendelfc.exception;

import org.springframework.security.core.AuthenticationException;

public class ExpiratedTokenException extends AuthenticationException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExpiratedTokenException(String msg, Throwable cause) {
		super(msg, cause);
	}


	
}
