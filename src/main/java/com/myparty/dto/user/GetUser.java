package com.myparty.dto.user;

import java.io.Serializable;

import lombok.Data;
import com.myparty.annotations.DataConverterType;
import com.myparty.converter.user.UserConverter;

@Data
@DataConverterType(UserConverter.class)
public class GetUser implements Serializable {
    
    private Long id;
    private String username;
    private String email;
    private String name;

}
