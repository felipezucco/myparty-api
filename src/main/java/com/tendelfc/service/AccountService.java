package com.tendelfc.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tendelfc.dto.AccountDTO;
import com.tendelfc.model.Account;
import com.tendelfc.repository.AccountRepository;

@Service
public class AccountService implements UserDetailsService {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return org.springframework.security.core.userdetails.User.builder()
				.username("fulano")
				.password(passwordEncoder.encode("123456"))
				.roles("USER", "ADMIN")
				.build();
	}

	public AccountDTO saveUser(AccountDTO accountDTO) {
		Account account = mapper.map(accountDTO, Account.class);
		accountRepository.save(account);
		accountDTO.setId(account.getId());
		return accountDTO;
	}
}
