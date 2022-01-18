package com.tendelfc.exception;

public class MismatchedPasswordException extends RuntimeException {

	public MismatchedPasswordException() {
		super("Senha incorreta!");
	}

}
