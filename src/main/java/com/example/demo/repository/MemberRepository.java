package com.example.demo.repository;

import com.example.demo.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

// public interface MemberRepository extends JpaRepository<Member, String> {
// }
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select concat(substring(curdate(),1,4),lpad(count(*)+1, '5','0') ) from Member m where year(m.createdDate) = year(curdate())")
    String makeId();
}