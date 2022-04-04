package com.myparty.exception;

public class MismatchedPasswordException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public MismatchedPasswordException() {
		super("Senha incorreta!");
	}

}
