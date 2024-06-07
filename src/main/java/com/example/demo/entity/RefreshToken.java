package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class RefreshToken {

    @Id
    @Column(name = "rt_key")
    private String key;

    @Column(name = "rt_value")
    private String value;

    private String ip;

    @Builder
    public RefreshToken(String key, String value,String ip) {
        this.key = key;
        this.value = value;
        this.ip = ip;
    }

    public RefreshToken updateValue(String token) {
        this.value = token;
        return this;
    }
}