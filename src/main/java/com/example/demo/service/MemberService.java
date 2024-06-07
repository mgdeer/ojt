package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.UserCreateForm;
import org.springframework.http.ResponseEntity;
import com.example.demo.entity.Member;

public interface MemberService {
    ResponseEntity<String> signup(UserCreateForm userCreateForm);

    List<Member> findAll();
    Member findById(Long id);
    void update(Member member);
    void delete(Long id);
    void updateMyInfo(Member member);


}
