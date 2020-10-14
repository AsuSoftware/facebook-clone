package com.asusoftware.facebookClone.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserDTO {

    private Long id;
    private String name;
    private String lastName;
    private Date birthday;
    private String gender;
    private String email;

    @JsonIgnore
    private String password;


}