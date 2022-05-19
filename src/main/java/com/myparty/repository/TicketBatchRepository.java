package com.myparty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myparty.model.TicketBatch;

@Repository
public interface TicketBatchRepository extends JpaRepository<TicketBatch, Long>{
}
