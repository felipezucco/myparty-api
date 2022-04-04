package com.myparty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myparty.model.Zone;


@Repository
public interface ZoneRepository extends JpaRepository<Zone, Long>{

}
