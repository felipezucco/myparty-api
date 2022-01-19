package com.tendelfc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.tendelfc.enums.RoleEnum;

import lombok.Data;

@Data
@Entity
@Table(uniqueConstraints = {
		@UniqueConstraint(columnNames = "username", name = "ACCOUNT_USERNAME_UK"),
		@UniqueConstraint(columnNames = "email", name = "ACCOUNT_EMAIL_UK")
})
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "account_id")
	private Long id;
	
	private String username;
	
	private String password;
	
	private String name;
	
	private String email;

	@Column(name = "role_number")
	private RoleEnum role = RoleEnum.USER;
	
	public static Account username(String username) {
		Account account = new Account();
		account.setUsername(username);
		return account;
	}
	
	public static Account email(String email) {
		Account account = new Account();
		account.setEmail(email);
		return account;
	}
	
}
