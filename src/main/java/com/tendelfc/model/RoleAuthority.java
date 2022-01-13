package com.tendelfc.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "role_authority")
public class RoleAuthority {	
	
	@Embeddable
	public class RoleAuthorityPK implements Serializable {
		private static final long serialVersionUID = 3844498290336373310L;

		@OneToOne
		@JoinColumn(name = "authority_id")
		private Authority authority;
		
		@OneToOne
		@JoinColumn(name = "role_id")
		private Role role;
		
	}

	@EmbeddedId
	private RoleAuthorityPK id;
	
}
