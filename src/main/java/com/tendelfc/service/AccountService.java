package com.tendelfc.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tendelfc.dto.AccountDTO;
import com.tendelfc.model.Account;
import com.tendelfc.repository.AccountRepository;

@Service
public class AccountService {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private ModelMapper mapper;

	public AccountDTO saveUser(AccountDTO accountDTO) {
		Account account = mapper.map(accountDTO, Account.class);
		account.setPassword(passwordEncoder.encode(account.getPassword()));
		accountRepository.save(account);
		accountDTO.setId(account.getId());
		return accountDTO;
	}
	
	public List<AccountDTO> getUsers() {
		return accountRepository.findAll().stream().map(a -> mapper.map(a, AccountDTO.class)).collect(Collectors.toList());
	}
	
	public void deleteUser(Long id) throws IllegalArgumentException {
		accountRepository.deleteById(id);
	}
}
