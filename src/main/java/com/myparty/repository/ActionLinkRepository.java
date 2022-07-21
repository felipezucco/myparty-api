package com.myparty.repository;

import com.myparty.model.action.ActionLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionLinkRepository extends JpaRepository<ActionLink, Long> {
}
