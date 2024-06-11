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

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

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
    public Member getMember(@PathVariable String id) {
        return memberService.findById(id);
    }

    // @PutMapping("/{id}")
    // public Member editMember(@PathVariable String id, @RequestBody Member member) {
    //     member.setId(id);
    //     memberService.update(member);
    //     return member;
    // }
//
//    @DeleteMapping("/{id}")
//    public void deleteMember(@PathVariable String id) {
//        memberService.delete(id);
//    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable String id) {
        memberService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me/{id}")
    public Member myInfo(@PathVariable String id) {
        return memberService.findById(id);
    }

    @PutMapping("/me")
    public Member updateMyInfo(@RequestBody Member member) {
        memberService.updateMyInfo(member);
        return member;
    }

    @PostMapping("/changePwd")
    public ResponseEntity<String> changePwd(@RequestBody ChangePasswordDto changePasswordDto){
        return memberService.changePassword(changePasswordDto);
    }

}
