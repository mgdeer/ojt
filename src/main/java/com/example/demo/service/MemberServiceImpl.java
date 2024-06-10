package com.example.demo.service;

import com.example.demo.Helper;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.SHA256;
import com.example.demo.dto.MemberRequestDto;
import com.example.demo.dto.MemberResponseDto;
import com.example.demo.dto.TokenRequestDto;
import com.example.demo.entity.Member;
import com.example.demo.dto.UserCreateForm;
import com.example.demo.entity.RefreshToken;
import com.example.demo.jwt.TokenDto;
import com.example.demo.jwt.TokenProvider;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.RefreshTokenRepository;
import jakarta.servlet.http.HttpServletRequest;
//import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    private final MemberRepository memberRepository;
    private final SHA256 sha256;

    @Override
    public ResponseEntity<String> signup(UserCreateForm userCreateForm) {
//        if(!userCreateForm.getPassword().equals(userCreateForm.getPasswordConfirm())){
//            return ResponseEntity.badRequest().body("Passwords do not match");
//        }
        try {
            LocalDate now = LocalDate.now();
            String count = memberRepository.makeId();
            String newId = now.getYear() + "0".repeat(5-count.length()) + count;
            String encryptedPassword = sha256.encrypt("multiojt19");
            Member member = Member.builder()
                    .id(Long.parseLong(newId))
                    .password(encryptedPassword)
                    .department(userCreateForm.getDepartment())
                    .name(userCreateForm.getName())
                    .role(userCreateForm.getRole())
                    .phone(userCreateForm.getPhone())
                    .salary(userCreateForm.getSalary())
                    .build();
            memberRepository.save(member);
            return ResponseEntity.ok("Signup successful");
        } catch (Exception e) {
            log.error("Signup failed", e);
            return ResponseEntity.status(500).body("Signup failed");
        }
    }

    @Override
    public MemberResponseDto findMemberInfoByEmail(String email) {
        return memberRepository.findByEmail(email)
                .map(MemberResponseDto::of)
                .orElseThrow(() -> new RuntimeException("유저 정보가 없습니다."));
    }

    @Transactional
    public TokenDto login(HttpServletRequest request, MemberRequestDto memberRequestDto) {

        memberRequestDto.setPassword(memberRequestDto.getEmail()+memberRequestDto.getPassword());
        // 1. Login ID/PW 를 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = memberRequestDto.toAuthentication();

        // 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        //    authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // 4. RefreshToken 저장
        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(tokenDto.getRefreshToken())
                .ip(Helper.getClientIp(request))
                .build();

        refreshTokenRepository.save(refreshToken);

        // 5. 토큰 발급
        return tokenDto;
    }

    @Transactional
    public TokenDto reissue(HttpServletRequest request, TokenRequestDto tokenRequestDto) {

        // 1. Refresh Token 검증
        if (!tokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("Refresh Token 이 유효하지 않습니다.");
        }

        // 2. Access Token 에서 Member ID 가져오기
        Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

        // 3. 저장소에서 Member ID 를 기반으로 Refresh Token 값 가져옴
        RefreshToken refreshToken = refreshTokenRepository.findByKey(authentication.getName())
                .orElseThrow(() -> new RuntimeException("로그아웃 된 사용자입니다."));

        // 4. Refresh Token 일치하는지 검사
        if (!refreshToken.getValue().equals(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("토큰의 유저 정보가 일치하지 않습니다.");
        }
        String currentIpAddress = Helper.getClientIp(request);
        if (refreshToken.getIp().equals(currentIpAddress)) {


            // 5. 새로운 토큰 생성
            TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

            // 6. 저장소 정보 업데이트
            RefreshToken newRefreshToken = refreshToken.updateValue(tokenDto.getRefreshToken());
            refreshTokenRepository.save(newRefreshToken);

            // 토큰 발급
            return tokenDto;
        } else {
            throw new RuntimeException("ip가 일치하지 않습니다.");
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
