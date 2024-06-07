package com.example.demo.service;

import java.util.List;

import com.example.demo.SHA256;
import com.example.demo.entity.Member;
import com.example.demo.dto.UserInfoForm;
import com.example.demo.repository.MemberRepository;
import jakarta.persistence.EntityManager;
//import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
//    private final EntityManager entityManager;
    private final SHA256 sha256;

    @Override
    public ResponseEntity<String> signup(UserInfoForm userCreateForm) {
//        if(!userCreateForm.getPassword().equals(userCreateForm.getPasswordConfirm())){
//            return ResponseEntity.badRequest().body("Passwords do not match");
//        }
        try {
            String encryptedPassword = sha256.encrypt("multiojt19");
            Member member = Member.builder()
                    .password(encryptedPassword)
                    .department(userCreateForm.getDepartment())
                    .name(userCreateForm.getName())
                    .role(userCreateForm.getRole())
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

//    @Override
//    @Transactional
//    public ResponseEntity<String> update(UserInfoForm userInfoForm) {
//        try{
//            String encryptedPassword = sha256.encrypt(userInfoForm.getPassword());
//            Member member = entityManager.find(Member.class, userInfoForm.getId());
//            if (!member.getPassword().equals(encryptedPassword)) {
//                member.setPassword(encryptedPassword);
//            }
//            if (!member.getDepartment().equals(userInfoForm.getDepartment())) {
//                member.setDepartment(userInfoForm.getDepartment());
//            }
//            if (!member.getName().equals(userInfoForm.getName())) {
//                member.setName(userInfoForm.getName());
//            }
//            if (!member.getRole().equals(userInfoForm.getRole())) {
//                member.setRole(userInfoForm.getRole());
//            }
//            if (!member.getEmail().equals(userInfoForm.getEmail())) {
//                member.setEmail(userInfoForm.getEmail());
//            }
//            if (!member.getPhoneNumber().equals(userInfoForm.getPhone())) {
//                member.setPhoneNumber(userInfoForm.getPhone());
//            }
//            if (!member.getNickname().equals(userInfoForm.getNickname())) {
//                member.setNickname(userInfoForm.getNickname());
//            }
//            if(member.getTemp().equals("o")){
//                member.setTemp("x");
//            }
//            return ResponseEntity.ok("Update successful");
//        } catch (Exception e){
//            log.error("Update failed", e);
//            return ResponseEntity.status(500).body("Update failed");
//        }
//    }
}
