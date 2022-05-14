/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myparty.converter;

import com.myparty.dto.UserWithoutPasswordDTO;
import com.myparty.interfaces.DataConverter;
import com.myparty.model.UserProfile;

/**
 *
 * @author Felipe Zucco
 */
public class UserWithoutPasswordConverter implements DataConverter<UserProfile, UserWithoutPasswordDTO>{
    
    @Override
    public UserWithoutPasswordDTO convert(UserProfile user) {
        UserWithoutPasswordDTO dto = new UserWithoutPasswordDTO();
        dto.setEmail(user.getEmail());
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setUsername(user.getUsername());
        return dto;
    }

    @Override
    public UserProfile revert(UserWithoutPasswordDTO dto) {
        UserProfile entity = new UserProfile();
        entity.setEmail(dto.getEmail());
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setUsername(dto.getUsername());
        return entity;
    }
    
}
