package com.myparty.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myparty.converter.user.UserConverter;
import org.hibernate.annotations.GenericGenerator;

import com.myparty.annotations.DataConverterType;
import com.myparty.converter.user.UserWithoutPasswordConverterOld;

import lombok.Data;


@Data
@Entity
@Table(name = "user_profile",
		uniqueConstraints = {
    @UniqueConstraint(columnNames = "username", name = "user_username_uk"),
    @UniqueConstraint(columnNames = "email", name = "user_email_uk")
})
@DataConverterType(UserConverter.class)
public class UserProfile implements Serializable {

    @Id
    @GenericGenerator(strategy = "uuid", name = "system-uuid")
    @GeneratedValue(generator = "uuid")
    private Long id;
    private String username;

    @JsonIgnore
    private String password;
    private String name;
    private String email;

    public UserProfile builder() {
        return new UserProfile();
    }

    public UserProfile id(long id) {
        setId(id);
        return this;
    }

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

}
