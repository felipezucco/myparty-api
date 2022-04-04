package com.myparty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myparty.model.Organizer;


@Repository
public interface OrganizerRepository extends JpaRepository<Organizer, Long>{

}
