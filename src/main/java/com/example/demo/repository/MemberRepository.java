package com.example.demo.repository;

import com.example.demo.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
   boolean existsByEmail(String email);

    Optional<Member> findByEmail(String email);

    @Query("select concat(substring(curdate(),1,4),lpad(count(*)+1, '5','0') ) from Member m where year(m.createdDate) = year(curdate())")
    String makeId();
}