package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "VARCHAR(256)", nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;
}
