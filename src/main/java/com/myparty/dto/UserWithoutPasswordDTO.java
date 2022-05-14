package com.myparty.dto;

import java.io.Serializable;
import lombok.Data;
import com.myparty.annotations.DataConverterType;
import com.myparty.converter.UserWithoutPasswordConverter;

@Data
@DataConverterType(value = UserWithoutPasswordConverter.class, dto = true)
public class UserWithoutPasswordDTO implements Serializable {
    
    private Long id;
    private String username;
    private String email;
    private String name;

}
