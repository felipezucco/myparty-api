package com.myparty.controller.middleware;

import com.myparty.controller.RootController;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.myparty.dto.UserDTO;
import com.myparty.dto.UserWithoutPasswordDTO;
import com.myparty.enums.UserSearchEnum;
import com.myparty.exception.NoValueFoundException;
import com.myparty.model.UserProfile;
import com.myparty.service.UserService;
import com.myparty.utils.Base64Decoder;
import java.util.stream.Collectors;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserMiddleware extends RootController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private PasswordEncoder encoder;

    public void persistUser(UserDTO userDTO) {
        userService.checkUsernameEmailAccount(userDTO.getUsername(), userDTO.getEmail());
        UserProfile user = data.convert(userDTO);
        user.setPassword(encoder.encode(user.getPassword()));
        userService.persistUser(user);
    }

    public List<UserWithoutPasswordDTO> getUsers() {
        return data.convert(userService.getUsers());
    }

    public void deleteUser(Long id) {
        userService.deleteUser(id);
    }

    public List<UserWithoutPasswordDTO> getUserByParam(UserSearchEnum q, String v) {
        List<UserProfile> users = userService.getUserStartsWith(q, v);
        return data.convert(users);
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
        List<UserProfile> users = userService.getUserStartsWith(UserSearchEnum.USERNAME, username);
        if (!users.isEmpty()) return data.convert(users.get(0));
        else throw new NoValueFoundException();
    }

}
