package com.tendelfc.exception;

public class AccountException extends RuntimeException {

	public AccountException(String msg) {
		super(msg);
	}
	
	public static class UsernameExistException extends AccountException {
		public UsernameExistException(String username) {
			super("Usuário ".concat(username).concat(" já existe."));
		}
	}
	
	public static class EmailExistException extends AccountException {
		public EmailExistException(String username) {
			super("Email ".concat(username).concat(" já existe."));
		}
	}
	
}
