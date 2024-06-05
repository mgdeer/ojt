package com.example.demo.dto;

import com.example.demo.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm {
//    private String id;
    private String name;
    private String email;
    //private String password;
    //private String passwordConfirm;
    private Role role;
    private String nickname;
    private String department;
}
