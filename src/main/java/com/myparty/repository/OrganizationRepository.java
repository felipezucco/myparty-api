package com.myparty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myparty.model.Organization;


@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long>{

}
