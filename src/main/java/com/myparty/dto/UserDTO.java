package com.myparty.dto;

import java.io.Serializable;
import lombok.Data;
import com.myparty.annotations.DataConverterType;
import com.myparty.converter.UserConverter;

@Data
@DataConverterType(value = UserConverter.class, dto = true)
public class UserDTO implements Serializable {
    
    private Long id;
    private String username;
    private String password;
    private String email;
    private String name;

}
