package com.myparty.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.myparty.dto.AccountDTO;
import com.myparty.exception.AccountException;
import com.myparty.model.Account;
import com.myparty.repository.AccountRepository;


@Service
public class AccountService {

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private ModelMapper mapper;

	public AccountDTO persistUser(AccountDTO accountDTO) {
		Account account = mapper.map(accountDTO, Account.class);
		account.setPassword(encoder.encode(account.getPassword()));
		accountRepository.save(account);
		accountDTO.setId(account.getId());
		return accountDTO;
	}
	
	public List<AccountDTO> getUsersByExample(Account accountExample) {
		Example<Account> example = Example.of(accountExample);
		return accountRepository.findAll(example).stream().map(a -> mapper.map(a, AccountDTO.class)).collect(Collectors.toList());
	}
	
	public List<AccountDTO> getUsers() {
		return accountRepository.findAll().stream().map(a -> mapper.map(a, AccountDTO.class)).collect(Collectors.toList());
	}
	
	public void deleteUser(Long id) throws IllegalArgumentException {
		accountRepository.deleteById(id);
	}
	
	public void checkUsernameEmailAccount(String username, String email) {
		// Check if username already exists
		List<AccountDTO> existUsername = getUsersByExample(Account.username(username));
		if (!existUsername.isEmpty()) throw new AccountException.UsernameExistException(username);

		// Check if email already exists
		List<AccountDTO> existEmail = getUsersByExample(Account.email(email));
		if (!existEmail.isEmpty()) throw new AccountException.EmailExistException(email);
	}
}
