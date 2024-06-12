package com.example.demo.service;

import com.example.demo.dto.*;

import java.util.List;

import com.example.demo.jwt.TokenDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import com.example.demo.entity.Member;

public interface MemberService {
    ResponseEntity<String> signup(UserCreateForm userCreateForm);

    MemberResponseDto findMemberInfoById(String email);
    MemberResponseDto MemberInfo(String id);
    MemberResponseDto updateMemberInfo(String id, MemberUpdateDto memberUpdateDto);


    TokenDto login(HttpServletRequest request, MemberRequestDto memberRequestDto);

    TokenDto reissue(HttpServletRequest request, TokenRequestDto tokenRequestDto);

//    ResponseEntity<String> update(UserInfoForm userInfoForm);

    List<Member> findAll();
    Member findById(String id);
//    void update(Member member);
    void delete(String id);
    // MemberResponseDto updateMyInfo(String id, MemberUpdateDto memberUpdateDto);
    ChangeMyInfoDto updateMyInfo(ChangeMyInfoDto changeMyInfoDto);
    ResponseEntity<Boolean> emailCheck(String email);
    ResponseEntity<Boolean> phoneCheck(String phone);
    ResponseEntity<String> changePassword(ChangePasswordDto changePasswordDto);
}
