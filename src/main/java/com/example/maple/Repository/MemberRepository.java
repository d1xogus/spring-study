package com.example.maple.Repository;

import com.example.maple.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
public interface MemberRepository extends JpaRepository<Member, String> {
}
