package com.example.demo.dto;

import com.example.demo.Role;
import com.example.demo.entity.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberResponseDto {

    private Long id;
    private String name;
    private String email;
    private String password;
    private Role role;
    private String nickname;
    private String department;

    public static MemberResponseDto of(Member member) {
        MemberResponseDto dto = new MemberResponseDto();
        dto.setId(member.getId());
        dto.setName(member.getName());
        dto.setEmail(member.getEmail());
        dto.setPassword(member.getPassword());
        dto.setRole(member.getRole());
        dto.setDepartment(member.getDepartment());
        return dto;
    }
}
