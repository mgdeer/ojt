package com.example.demo.service;

import com.example.demo.dto.UserCreateForm;
import org.springframework.http.ResponseEntity;

public interface MemberService {
    ResponseEntity<String> signup(UserCreateForm userCreateForm);
}
