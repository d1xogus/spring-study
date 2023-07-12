package com.example.maple.Service;

import com.example.maple.DTO.MemberDTO;
import com.example.maple.Entity.Member;
import com.example.maple.Repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MemberService {
    private MemberRepository memberRepository;
    public List<Member> index() {
        return memberRepository.findAll();
    }

    public Member create(MemberDTO memberDTO) {
        Member member = memberDTO.toMemberEntity(memberDTO);
        return memberRepository.save(member);
    }

    public Member update(String m_id, MemberDTO memberDTO) {
        Member member = memberDTO.toupdateEntity(memberDTO);
        Member target = memberRepository.findById(m_id).orElse(null);
        target.patch(member);
        Member updated = memberRepository.save(target);
        return updated;
    }

    public Member delete(String m_id) {
        Member target = memberRepository.findById(m_id).orElse(null);
        memberRepository.delete(target);
        return target;
    }
}
