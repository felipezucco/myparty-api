package com.myparty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myparty.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>{

}