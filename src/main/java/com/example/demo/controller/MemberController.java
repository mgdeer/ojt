package com.example.demo.controller;

import java.util.List;

import com.example.demo.service.MemberService;
import com.example.demo.dto.UserCreateForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.Member;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/create")
    public ResponseEntity<String> signup(@RequestBody UserCreateForm userCreateForm) {
        return memberService.signup(userCreateForm);
    }

    @GetMapping
    public List<Member> listMembers() {
        return memberService.findAll();
    }

    @GetMapping("/{id}")
    public Member getMember(@PathVariable Long id) {
        return memberService.findById(id);
    }

    @PutMapping("/{id}")
    public Member editMember(@PathVariable Long id, @RequestBody Member member) {
        member.setId(id);
        memberService.update(member);
        return member;
    }

    @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable Long id) {
        memberService.delete(id);
    }

    @GetMapping("/me")
    public Member myInfo() {
        // Assume we can get the logged-in user's ID from a security context or session
        Long loggedInUserId = 1L; // Placeholder for actual user ID retrieval logic
        return memberService.findById(loggedInUserId);
    }

    @PutMapping("/me")
    public Member updateMyInfo(@RequestBody Member member) {
        memberService.updateMyInfo(member);
        return member;
    }


}
