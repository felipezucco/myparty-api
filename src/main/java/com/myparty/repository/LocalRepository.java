package com.myparty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myparty.model.Local;
import java.util.List;

@Repository
public interface LocalRepository extends JpaRepository<Local, Long>{

    List<Local> findByOrganizationId(Long id);
}
