package com.example.demo.dto;

import com.example.demo.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm {
    private String name;
    private Role role;
    private String department;
    private String email;
    private String phone;
    private int salary;
}
