package com.myparty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myparty.model.organization.Organization;
import com.myparty.model.organization.Organizer;

import java.util.List;


@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long>{

}
