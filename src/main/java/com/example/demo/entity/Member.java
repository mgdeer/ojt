package com.example.demo.entity;

import com.example.demo.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Member extends BaseTimeEntity{
    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 64)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private String department;
}
