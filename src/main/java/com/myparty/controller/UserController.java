package com.myparty.controller;

import com.myparty.dto.user.PersistUser;
import com.myparty.enums.UserSearchEnum;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.myparty.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myparty.dto.user.GetUser;
import com.myparty.utils.Base64Decoder;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/api/user")
public class UserController extends ControllerComponent {

    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void persistUser(@RequestBody PersistUser persistUser) {
        userService.checkUsernameEmailAccount(persistUser.getUsername(), persistUser.getEmail());
        userService.persistUser(_8(persistUser));
    }

    @GetMapping
    public ResponseEntity<List<GetUser>> getUsers() {
        return ResponseEntity.ok(_8(userService.getUsers()));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/s")
    public ResponseEntity<List<GetUser>> getUserSearch(@RequestParam String q, @RequestParam String v) throws UnsupportedEncodingException {
        String query = Base64Decoder.decode(q);
        String value = Base64Decoder.decode(v);
        return ResponseEntity.ok(_8(userService.getUserStartsWith(UserSearchEnum.EMAIL, value)));
    }

}
