package com.tendelfc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tendelfc.model.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

}
