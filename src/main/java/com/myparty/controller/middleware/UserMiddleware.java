package com.myparty.controller.middleware;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.myparty.dto.UserDTO;
import com.myparty.dto.UserWithoutPasswordDTO;
import com.myparty.enums.UserSearchEnum;
import com.myparty.exception.NoValueFoundException;
import com.myparty.model.UserProfile;
import com.myparty.service.UserService;

@Service
public class UserMiddleware extends RootMiddleware {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder encoder;

    public void persistUser(UserDTO userDTO) {
        userService.checkUsernameEmailAccount(userDTO.getUsername(), userDTO.getEmail());
        UserProfile user = convert(userDTO);
        user.setPassword(encoder.encode(user.getPassword()));
        userService.persistUser(user);
    }

    public List<UserWithoutPasswordDTO> getUsers() {
        return convert(userService.getUsers());
    }

    public void deleteUser(Long id) {
        userService.deleteUser(id);
    }

    public List<UserWithoutPasswordDTO> getUserByParam(UserSearchEnum q, String v) {
        List<UserProfile> users = userService.getUserStartsWith(q, v);
        return convert(users);
    }

    public List<UserWithoutPasswordDTO> getUserSearch(String q, String v) {
        switch (q.toUpperCase()) {
            case "EMAIL":
                return getUserByParam(UserSearchEnum.EMAIL, v);
            default:
                throw new AssertionError();
        }
    }

    public UserWithoutPasswordDTO getUserByUsername(String username) {
        UserProfile user = userService.getUserByUsername(username);
        if (user != null) {
			return convert(user);
		}
		throw new NoValueFoundException();
    }

}
