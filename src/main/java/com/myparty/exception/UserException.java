package com.myparty.exception;

public class UserException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserException(String msg) {
		super(msg);
	}
	
	public static class UsernameExistException extends UserException {
		private static final long serialVersionUID = 1L;

		public UsernameExistException(String username) {
			super("Usuário ".concat(username).concat(" já existe."));
		}
	}
	
	public static class EmailExistException extends UserException {
		private static final long serialVersionUID = 1L;

		public EmailExistException(String username) {
			super("Email ".concat(username).concat(" já existe."));
		}
	}
	
}
