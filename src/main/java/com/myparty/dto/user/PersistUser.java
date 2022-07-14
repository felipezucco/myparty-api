package com.myparty.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myparty.annotations.DataConverterType;
import com.myparty.converter.user.UserConverter;
import lombok.Data;

import java.io.Serializable;

@Data
@DataConverterType(UserConverter.class)
public class PersistUser implements Serializable {

    private String username;
    private String password;
    private String email;
    private String name;

}
