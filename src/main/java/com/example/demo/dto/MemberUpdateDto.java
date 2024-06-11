package com.example.demo.dto;

import com.example.demo.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberUpdateDto {
    private String name;
    private String email;
    private String phone;
    private int salary;
    private Role role;
    private String department;
}