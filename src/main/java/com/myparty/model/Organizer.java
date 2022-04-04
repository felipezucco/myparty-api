package com.myparty.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import com.myparty.enums.RoleEnum;

import lombok.Data;

@Data
@Entity
public class Organizer {

	@Id
	@GenericGenerator(strategy = "uuid", name = "system-uuid")
	@GeneratedValue(generator = "uuid")
	@Column(name = "organizer_id")
	private Long id;

	@JoinColumn(name = "account_id", referencedColumnName = "account_id",  foreignKey = @ForeignKey(name = "organizer_account_fk"))
	@OneToOne
	private Account account;

	@Column(name = "role_number")
	private RoleEnum role = RoleEnum.USER;

	@ManyToOne
	@JoinColumn(name = "organization_id", referencedColumnName = "organization_id", foreignKey = @ForeignKey(name = "organizer_organization_fk"))
	private Organization organization;
}
