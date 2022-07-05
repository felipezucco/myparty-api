package com.myparty.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myparty.model.UserProfile;



@Repository
public interface UserRepository extends JpaRepository<UserProfile, Long>{

	UserProfile findByUsername(String username);
	List<UserProfile> findByEmailStartsWith(String email);

	@Query("select u.name from user_profile u where u.id = :id")
	UserProfile getNameById(@Param("id") Long id);
}
