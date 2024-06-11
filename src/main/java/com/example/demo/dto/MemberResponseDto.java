package com.example.demo.dto;

import com.example.demo.Role;
import com.example.demo.entity.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberResponseDto {

    private String id;
    private String name;
    private String email;
    private String phone;
    private Role role;
    private String department;
    private int salary;


    public static MemberResponseDto of(Member member) {
        MemberResponseDto dto = new MemberResponseDto();
        dto.setId(member.getId());
        dto.setName(member.getName());
        dto.setEmail(member.getEmail());
//        dto.setPassword(member.getPassword());
        dto.setRole(member.getRole());
        dto.setPhone(member.getPhone());
        dto.setDepartment(member.getDepartment());
        dto.setSalary(member.getSalary());
        return dto;
    }
}
