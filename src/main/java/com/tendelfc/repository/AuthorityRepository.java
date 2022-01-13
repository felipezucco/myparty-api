package com.tendelfc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tendelfc.model.Authority;


@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long>{

}
