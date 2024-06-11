package com.example.demo.repository;

import com.example.demo.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByid(String id);

    @Query("select m from Member m where m.id = :id")
    Optional<Member> findByid(String id);

    @Query("select count(*) + 1 from Member m where year(m.createdDate) = year(curdate())")
    String makeId();

    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);

    void deleteById(String id);
}