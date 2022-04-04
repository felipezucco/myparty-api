package com.myparty.exception;

public class AccountException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AccountException(String msg) {
		super(msg);
	}
	
	public static class UsernameExistException extends AccountException {
		private static final long serialVersionUID = 1L;

		public UsernameExistException(String username) {
			super("Usuário ".concat(username).concat(" já existe."));
		}
	}
	
	public static class EmailExistException extends AccountException {
		private static final long serialVersionUID = 1L;

		public EmailExistException(String username) {
			super("Email ".concat(username).concat(" já existe."));
		}
	}
	
}
