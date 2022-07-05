/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myparty.converter;

import com.myparty.dto.UserDTO;
import com.myparty.dto.UserWithoutPasswordDTO;
import com.myparty.interfaces.DataConverter;
import com.myparty.model.UserProfile;
import org.springframework.stereotype.Component;

/**
 *
 * @author Felipe Zucco
 */
@Component
public class UserConverter implements DataConverter<UserProfile, UserDTO>{

    @Override
    public UserDTO convert(UserProfile user) {
        UserDTO dto = new UserDTO();
        dto.setEmail(user.getEmail());
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setPassword(user.getPassword());
        dto.setUsername(user.getUsername());
        return dto;
    }

    @Override
    public UserProfile revert(UserDTO dto) {
        UserProfile entity = new UserProfile();
        entity.setEmail(dto.getEmail());
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setPassword(dto.getPassword());
        entity.setUsername(dto.getUsername());
        return entity;
    }

}
