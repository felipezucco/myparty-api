package com.tendelfc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tendelfc.model.Zone;


@Repository
public interface ZonesRepository extends JpaRepository<Zone, Long>{

}
