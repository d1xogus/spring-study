package com.example.maple.Repository;

import com.example.maple.Entity.Member;
//import com.example.maple.Entity.MemberDetails;
import org.hibernate.sql.ordering.antlr.ColumnMapper;
import org.springframework.data.jpa.repository.JpaRepository;
public interface MemberRepository extends JpaRepository<Member, Long> {
//    MemberDetails getByLoginId(String loginId);

    <Optional>Member findByLoginId(String loginId);
}
