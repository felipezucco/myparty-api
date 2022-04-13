package com.myparty.service;

import com.myparty.enums.UserSearchEnum;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.myparty.exception.UserException;
import com.myparty.model.UserProfile;
import com.myparty.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

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

    public List<UserProfile> getUserStartsWith(UserSearchEnum attr, String value) {
        switch (attr) {
            case EMAIL:
                return userRepository.findByEmailStartsWith(value);
            case USERNAME:
                return userRepository.findByUsername(value);
            default:
                return null;
        }
    }
}
