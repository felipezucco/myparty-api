package com.myparty.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myparty.model.Account;



@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
	
	Optional<Account> findByUsername(String username);
	List<Account> findByEmailStartsWith(String email);
}
