package com.example.demo.service;

import java.util.List;

import com.example.demo.SHA256;
import com.example.demo.entity.Member;
import com.example.demo.dto.UserCreateForm;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final SHA256 sha256;

    @Override
    public ResponseEntity<String> signup(UserCreateForm userCreateForm) {
//        if(!userCreateForm.getPassword().equals(userCreateForm.getPasswordConfirm())){
//            return ResponseEntity.badRequest().body("Passwords do not match");
//        }
        try {
            String encryptedPassword = sha256.encrypt(userCreateForm.getEmail()
                    + "multiojt19");
            Member member = Member.builder()
                    .password(encryptedPassword)
                    .department(userCreateForm.getDepartment())
                    .name(userCreateForm.getName())
                    .email(userCreateForm.getEmail())
                    .role(userCreateForm.getRole())
                    .nickname(userCreateForm.getNickname())
                    .build();
            memberRepository.save(member);
            return ResponseEntity.ok("Signup successful");
        } catch (Exception e) {
            log.error("Signup failed", e);
            return ResponseEntity.status(500).body("Signup failed");
        }
    }
    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    public Member findById(Long id) {
        return memberRepository.findById(id).orElse(null);
    }

    @Override
    public void update(Member member) {
        memberRepository.save(member);
    }

    @Override
    public void delete(Long id) {
        memberRepository.deleteById(id);
    }

    @Override
    public void updateMyInfo(Member member) {
        Member existingMember = memberRepository.findById(member.getId()).orElse(null);
        if (existingMember != null) {
            existingMember.setEmail(member.getEmail());
            // existingMember.setPhoneNumber(member.getPhoneNumber()); 연락처
            existingMember.setName(member.getName());
            memberRepository.save(existingMember);
        }
    }
}
