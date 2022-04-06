package com.myparty.exception;

import lombok.NoArgsConstructor;

public class AuthException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AuthException(String msg) {
		super(msg);
	}

	public static class BadCredentialException extends AuthException {
		private static final long serialVersionUID = 1L;

		public BadCredentialException() {
			super("Usuário e/ou senha inválidos.");
		}

	}

}
