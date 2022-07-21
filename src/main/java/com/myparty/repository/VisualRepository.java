package com.myparty.repository;

import com.myparty.model.Visual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisualRepository extends JpaRepository<Visual, Long> {

    List<Visual> findByEventId(Long eventId);
}
