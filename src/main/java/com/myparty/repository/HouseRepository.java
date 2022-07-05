package com.myparty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myparty.model.house.House;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface HouseRepository extends JpaRepository<House, Long>{

    @Query("SELECT h FROM House h INNER JOIN Local l ON l = h.local WHERE l.organization.id = :id")
    List<House> findHouseByOrganizationId(@Param("id") Long id);
}
