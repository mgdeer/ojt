package com.example.demo.repository;

import com.example.demo.entity.Member;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
   boolean existsByEmail(String email);

    Optional<Member> findByEmail(String email);
}