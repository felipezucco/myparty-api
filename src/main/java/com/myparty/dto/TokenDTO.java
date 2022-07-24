package com.myparty.dto;

import com.myparty.dto.user.GetUser;
import com.myparty.dto.user.GetUserWithPassword;
import lombok.Data;

import java.io.Serializable;

@Data
public class TokenDTO implements Serializable {

    private GetUser user;
    private String token;
}
