package com.myparty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myparty.model.Local;

@Repository
public interface LocalRepository extends JpaRepository<Local, Long>{

}
