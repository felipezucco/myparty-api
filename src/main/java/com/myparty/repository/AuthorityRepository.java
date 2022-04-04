package com.myparty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myparty.model.Authority;


@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long>{

}
