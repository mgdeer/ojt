package com.example.demo.repository;

import com.example.demo.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByid(String id);

    Optional<Member> findByid(String id);

    @Query("select m.id from Member m where year(m.createdDate) = year(curdate()) order by m.createdDate desc LIMIT 1")
    String makeId();

    @Query("select count(*) from Member m where year(m.createdDate) = :year")
    long countForCount(int year);

    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);

    void deleteById(String id);

    @Query("select m from Member m order by field(m.role, 'ROLE_CHEIF', 'ROLE_ADMINISTRATOR', 'ROLE_USER')")
    List<Member> findAllNewOrder();


}