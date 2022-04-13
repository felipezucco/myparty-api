package com.myparty.dto;

import com.myparty.annotations.DTO;
import com.myparty.model.UserProfile;
import java.io.Serializable;
import lombok.Data;

 @Data
@DTO(UserProfile.class)
public class UserWithoutPasswordDTO implements Serializable {
    
    private Long id;
    private String username;
    private String email;
    private String name;

}
