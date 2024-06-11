package com.example.demo.service;

import com.example.demo.dto.MemberRequestDto;
import com.example.demo.dto.MemberResponseDto;
import com.example.demo.dto.TokenRequestDto;
import java.util.List;

import com.example.demo.dto.UserCreateForm;
import com.example.demo.jwt.TokenDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import com.example.demo.entity.Member;

public interface MemberService {
    ResponseEntity<String> signup(UserCreateForm userCreateForm);

    MemberResponseDto findMemberInfoById(String email);

    TokenDto login(HttpServletRequest request, MemberRequestDto memberRequestDto);

    TokenDto reissue(HttpServletRequest request, TokenRequestDto tokenRequestDto);

//    ResponseEntity<String> update(UserInfoForm userInfoForm);

    List<Member> findAll();
    Member findById(Long id);
    void update(Member member);
    void delete(Long id);
    void updateMyInfo(Member member);

    ResponseEntity<Boolean> emailCheck(String email);
    ResponseEntity<Boolean> phoneCheck(String phone);
}
