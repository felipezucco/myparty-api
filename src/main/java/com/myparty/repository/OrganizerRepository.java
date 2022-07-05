package com.myparty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myparty.model.UserProfile;
import com.myparty.model.organization.Organizer;

import java.util.List;

@Repository
public interface OrganizerRepository extends JpaRepository<Organizer, Long> {

    List<Organizer> findByUser(UserProfile user);
}
