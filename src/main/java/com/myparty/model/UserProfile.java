package com.myparty.model;

import com.myparty.dto.UserDTO;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

import com.myparty.enums.RoleEnum;
import java.io.Serializable;

import lombok.Data;
import static org.modelmapper.Converters.Collection.map;
import org.modelmapper.ModelMapper;

@Data
@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = "username", name = "user_username_uk"),
    @UniqueConstraint(columnNames = "email", name = "user_email_uk")
})
public class UserProfile implements Serializable {
    
    @Id
    @GenericGenerator(strategy = "uuid", name = "system-uuid")
    @GeneratedValue(generator = "uuid")
    private Long id;

    private String username;

    private String password;

    private String name;

    private String email;

    @Column(name = "role_number")
    private RoleEnum role = RoleEnum.USER;

    public static UserProfile username(String username) {
        UserProfile userProfile = new UserProfile();
        userProfile.setUsername(username);
        return userProfile;
    }

    public static UserProfile email(String email) {
        UserProfile userProfile = new UserProfile();
        userProfile.setEmail(email);
        return userProfile;
    }

    public UserDTO toDTO(ModelMapper map) {
        return map.map(this, UserDTO.class);
    }
    
}
