package com.myparty.dto.user;

import java.io.Serializable;

import com.myparty.converter.user.UserConverter;
import lombok.Data;
import com.myparty.annotations.DataConverterType;
import com.myparty.converter.user.UserWithoutPasswordConverterOld;

@Data
@DataConverterType(UserConverter.class)
public class GetUserWithPassword extends GetUser implements Serializable {
    
    private String password;

}
