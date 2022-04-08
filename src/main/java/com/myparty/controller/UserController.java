package com.myparty.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.stream.Collectors;

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

import com.myparty.dto.UserDTO;
import com.myparty.service.UserService;
import com.myparty.utils.Base64Decoder;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> persistUser(@RequestBody UserDTO userDTO) {
        userService.checkUsernameEmailAccount(userDTO.getUsername(), userDTO.getEmail());
        userService.persistUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
    }

    @GetMapping
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/s")
    public ResponseEntity<List<String>> getUserByParam(@RequestParam String q, @RequestParam String v) throws UnsupportedEncodingException {
        return ResponseEntity.ok(userService.getUserStartsWith(Base64Decoder.decode(q), Base64Decoder.decode(v))
                .parallelStream().map(user -> user.getEmail()).collect(Collectors.toList()));
    }

}
