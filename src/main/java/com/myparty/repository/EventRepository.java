package com.myparty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myparty.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long>{

}
