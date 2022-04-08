package com.myparty.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "event_organization")
public class EventOrganization {	
	
	@Embeddable
	public class EventOrganizationPK implements Serializable {
		private static final long serialVersionUID = 1L;

		@OneToOne
		@JoinColumn(name = "organization_id", foreignKey = @ForeignKey(name = "event_organization_organization_fk"))
		private Organization organization;
		
		@OneToOne
		@JoinColumn(name = "event_id", foreignKey = @ForeignKey(name = "event_organization_event_fk"))
		private Event event;
		
	}

	@EmbeddedId
	private EventOrganizationPK id;
	
}
