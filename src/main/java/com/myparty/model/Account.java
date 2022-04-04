package com.myparty.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

import com.myparty.enums.RoleEnum;

import lombok.Data;

@Data
@Entity
@Table(uniqueConstraints = {
		@UniqueConstraint(columnNames = "username", name = "ACCOUNT_USERNAME_UK"),
		@UniqueConstraint(columnNames = "email", name = "ACCOUNT_EMAIL_UK")
})
public class Account {

	@Id
	@GenericGenerator(strategy = "uuid", name = "system-uuid")
	@GeneratedValue(generator = "uuid")
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
