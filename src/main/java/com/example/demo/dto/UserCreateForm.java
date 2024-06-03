package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm {
    private String id;
    private String name;
    private String email;
    private String password;
    private String passwordConfirm;
}
