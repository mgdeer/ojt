package com.example.demo.controller;

import java.util.List;

import com.example.demo.dto.*;
import com.example.demo.entity.Member;
import com.example.demo.jwt.TokenDto;
import com.example.demo.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.jwt.TokenProvider;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    //
    private final TokenProvider tokenProvider;

    @PostMapping("/create")
    public ResponseEntity<String> signup(@RequestBody UserCreateForm userCreateForm) {
        return memberService.signup(userCreateForm);
    }
//    @GetMapping("/{id}")
//    public ResponseEntity<MemberResponseDto> findMemberInfoByEmail(@PathVariable(name = "id") String id) {
//        return ResponseEntity.ok(memberService.findMemberInfoById(id));
//    }
    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody MemberRequestDto memberRequestDto, HttpServletRequest request) {
        return ResponseEntity.ok(memberService.login(request, memberRequestDto));
    }

    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto, HttpServletRequest request) {
        return ResponseEntity.ok(memberService.reissue(request, tokenRequestDto));
    }

    @GetMapping("/emailCheck/{email}")
    public ResponseEntity<Boolean> emailCheck(@PathVariable(name = "email") String email) {
        return memberService.emailCheck(email);
    }

    @GetMapping("/phoneCheck/{phone}")
    public ResponseEntity<Boolean> phoneCheck(@PathVariable(name = "phone") String phone) {
        return memberService.phoneCheck(phone);
    }

    @GetMapping("/getAllMember")
    public ResponseEntity<List<Member>> listMembers() {
        List<Member> members = memberService.findAll();
        return ResponseEntity.ok(members);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto> getMember(@PathVariable String id) {
        return ResponseEntity.ok(memberService.MemberInfo(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberResponseDto> updateMember(@PathVariable String id, @RequestBody MemberUpdateDto memberUpdateDto) {
        return ResponseEntity.ok(memberService.updateMemberInfo(id, memberUpdateDto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable String id) {
        memberService.delete(id);
        return ResponseEntity.ok().build();
    }

    // @PutMapping("/me")
    // public ResponseEntity<MemberResponseDto> updateMyInfo(@RequestBody MemberUpdateDto memberUpdateDto, HttpServletRequest request) {
    //     String userId = (String) request.getSession().getAttribute("userId");
    //     return ResponseEntity.ok(memberService.updateMyInfo(userId, memberUpdateDto));
    // }
    @PutMapping("/me")
    public ResponseEntity<ChangeMyInfoDto> updateMyInfo(@RequestBody ChangeMyInfoDto changeMyInfoDto) {
        return ResponseEntity.ok(memberService.updateMyInfo(changeMyInfoDto));
    }

    @PostMapping("/changePwd")
    public ResponseEntity<String> changePwd(@RequestBody ChangePasswordDto changePasswordDto){
        return memberService.changePassword(changePasswordDto);
    }

}
