package com.myparty.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.myparty.dto.UserDTO;
import com.myparty.exception.UserException;
import com.myparty.model.UserProfile;
import com.myparty.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    public UserDTO persistUser(UserDTO userDTO) {
        UserProfile user = mapper.map(userDTO, UserProfile.class);
        user.setPassword(encoder.encode(user.getPassword()));
        persistUser(user);
        userDTO.setId(user.getId());
        return userDTO;
    }

    public UserProfile persistUser(UserProfile user) {
        userRepository.save(user);
        return user;
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
        List<UserDTO> existUsername = getUsersByExample(UserProfile.username(username)).stream().map(a -> mapper.map(a, UserDTO.class)).collect(Collectors.toList());
        if (!existUsername.isEmpty()) {
            throw new UserException.UsernameExistException(username);
        }

        // Check if email already exists
        List<UserDTO> existEmail = getUsersByExample(UserProfile.email(email)).stream().map(a -> mapper.map(a, UserDTO.class)).collect(Collectors.toList());
        if (!existEmail.isEmpty()) {
            throw new UserException.EmailExistException(email);
        }
    }

    public List<UserProfile> getUserStartsWith(String attr, String value) {
        switch (attr) {
            case "email":
                return userRepository.findByEmailStartsWith(value);
            case "username":
                return userRepository.findByUsername(value);
            default:
                return null;
        }
    }
}
