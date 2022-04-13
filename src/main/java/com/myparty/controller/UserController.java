package com.myparty.controller;

import com.myparty.controller.middleware.UserMiddleware;
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
import com.myparty.dto.UserWithoutPasswordDTO;
import com.myparty.utils.Base64Decoder;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserMiddleware middleware;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void persistUser(@RequestBody UserDTO userDTO) {
        middleware.persistUser(userDTO);
    }

    @GetMapping
    public ResponseEntity<List<UserWithoutPasswordDTO>> getUsers() {
        return ResponseEntity.ok(middleware.getUsers());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable("id") Long id) {
        middleware.deleteUser(id);
    }

    @GetMapping("/s")
    public ResponseEntity<List<UserWithoutPasswordDTO>> getUserSearch(@RequestParam String q, @RequestParam String v) throws UnsupportedEncodingException {
        String query = Base64Decoder.decode(q);
        String value = Base64Decoder.decode(v);
        return ResponseEntity.ok(middleware.getUserSearch(query, value));
    }

}
