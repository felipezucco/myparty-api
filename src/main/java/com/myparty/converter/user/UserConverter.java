/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myparty.converter.user;

import com.myparty.converter.ConverterComponent;
import com.myparty.dto.user.GetUserWithPassword;
import com.myparty.dto.user.PersistUser;
import com.myparty.dto.user.GetUser;
import com.myparty.interfaces.DataConverterInterface;
import com.myparty.model.UserProfile;
import com.myparty.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 *
 * @author Felipe Zucco
 */
@Component
@AllArgsConstructor
public class UserConverter extends ConverterComponent implements DataConverterInterface<UserProfile> {

    private UserService userService;

    @Override
    public <T> T convert(UserProfile entity, T destinationClass) {
        if (destinationClass == null) {
            destinationClass = getDefault(destinationClass, GetUser.class);
        }

        if (destinationClass instanceof GetUser) {
            GetUser uwp = (GetUser) destinationClass;
            uwp.setId(entity.getId());
            uwp.setEmail(entity.getEmail());
            uwp.setName(entity.getName());
            uwp.setUsername(entity.getUsername());
        }
        if (destinationClass instanceof GetUserWithPassword) {
            GetUserWithPassword guwp = (GetUserWithPassword) destinationClass;
            guwp.setPassword(entity.getPassword());
        }

        return destinationClass;
    }

    @Override
    public UserProfile revert(Object o) {
        UserProfile entity = new UserProfile();

        if (o instanceof PersistUser) {
            PersistUser pu = (PersistUser) o;
            entity.setEmail(pu.getEmail());
            entity.setName(pu.getName());
            entity.setPassword(userService.getPasswordEncoder().encode(pu.getPassword()));
            entity.setUsername(pu.getUsername());
        }

        return entity;
    }
}
