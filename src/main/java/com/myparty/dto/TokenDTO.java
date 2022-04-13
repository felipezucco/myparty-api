package com.myparty.dto;

import lombok.Data;

@Data
public class TokenDTO {

    private UserWithoutPasswordDTO user;
    private String token;
}
