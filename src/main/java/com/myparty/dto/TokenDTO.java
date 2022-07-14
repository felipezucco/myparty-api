package com.myparty.dto;

import com.myparty.dto.user.GetUserWithPassword;
import lombok.Data;

import java.io.Serializable;

@Data
public class TokenDTO implements Serializable {

    private GetUserWithPassword user;
    private String token;
}
