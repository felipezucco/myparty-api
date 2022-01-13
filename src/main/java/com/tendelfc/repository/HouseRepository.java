package com.tendelfc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tendelfc.model.House;


@Repository
public interface HouseRepository extends JpaRepository<House, Long>{

}
