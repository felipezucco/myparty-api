/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myparty.converter.user;

import com.myparty.dto.user.GetUserWithPassword;
import com.myparty.interfaces.OldDataConverter;
import com.myparty.model.UserProfile;
import org.springframework.stereotype.Component;

/**
 *
 * @author Felipe Zucco
 */
@Component
public class UserWithoutPasswordConverterOld implements OldDataConverter<UserProfile, GetUserWithPassword> {
    
    @Override
    public GetUserWithPassword convert(UserProfile user) {
        GetUserWithPassword dto = new GetUserWithPassword();
        dto.setEmail(user.getEmail());
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setUsername(user.getUsername());
        return dto;
    }

    @Override
    public UserProfile revert(GetUserWithPassword dto) {
        UserProfile entity = new UserProfile();
        entity.setEmail(dto.getEmail());
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setUsername(dto.getUsername());
        return entity;
    }
    
}
