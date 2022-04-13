package com.myparty.dto;

import com.myparty.annotations.DTO;
import com.myparty.model.UserProfile;
import java.io.Serializable;
import lombok.Data;

 @Data
@DTO(UserProfile.class)
public class UserDTO implements Serializable {
    
    private Long id;
    private String username;
    private String password;
    private String email;
    private String name;

}
