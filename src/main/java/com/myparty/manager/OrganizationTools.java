package com.myparty.manager;

import java.util.List;
import java.util.stream.Collectors;

import com.myparty.model.organization.Organization;

public final class OrganizationTools {

	private OrganizationTools() throws IllegalStateException {
		throw new IllegalStateException("Utility class");
	}

	public static List<String> getOrganizersUsername(Organization organization) {
		return organization.getOrganizers().stream().map(organizer -> organizer.getUser().getUsername()).collect(Collectors.toList());
	}

}
