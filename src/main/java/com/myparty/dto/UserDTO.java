package com.myparty.dto;

import java.io.Serializable;
import lombok.Data;

@Data
public class UserDTO implements Serializable {
    
    private Long id;
    private String username;
    private String password;
    private String email;
    private String name;

    public void entity() {
        
    }

}
