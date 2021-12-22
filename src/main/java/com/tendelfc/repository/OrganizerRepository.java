package com.tendelfc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tendelfc.model.Organizer;

@Repository
public interface OrganizerRepository extends JpaRepository<Organizer, Long>{

}
