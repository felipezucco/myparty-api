package com.myparty.service;

import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.myparty.enums.UserSearchEnum;
import com.myparty.exception.UserException;
import com.myparty.model.UserProfile;
import com.myparty.repository.UserRepository;

@Service
@Data
@AllArgsConstructor
public class UserService {

	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;

	public void persistUser(UserProfile user) {
		userRepository.save(user);
	}

	public List<UserProfile> getUsersByExample(UserProfile accountExample) {
		Example<UserProfile> example = Example.of(accountExample);
		return userRepository.findAll(example);
	}

	public List<UserProfile> getUsers() {
		return userRepository.findAll();
	}

	public UserProfile getUserById(Long id) {
		return userRepository.getById(id);
	}

	public String getNameById(Long id) {
		UserProfile user = userRepository.getNameById(id);
		if (Optional.ofNullable(user).isPresent()) {
			return user.getName();
		}

		return null;
	}

	public void deleteUser(Long id) throws IllegalArgumentException {
		userRepository.deleteById(id);
	}

	public void checkUsernameEmailAccount(String username, String email) {
		// Check if username already exists
		List<UserProfile> existUsername = getUsersByExample(UserProfile.username(username));
		if (!existUsername.isEmpty()) {
			throw new UserException.UsernameExistException(username);
		}

		// Check if email already exists
		List<UserProfile> existEmail = getUsersByExample(UserProfile.email(email));
		if (!existEmail.isEmpty()) {
			throw new UserException.EmailExistException(email);
		}
	}

	public UserProfile getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public List<UserProfile> getUserStartsWith(UserSearchEnum attr, String value) {
		switch (attr) {
		case EMAIL:
			return userRepository.findByEmailStartsWith(value);
		default:
			return null;
		}
	}
}
