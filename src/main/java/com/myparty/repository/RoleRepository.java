package com.myparty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myparty.model.role.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

}
