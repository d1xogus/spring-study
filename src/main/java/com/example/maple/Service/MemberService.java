package com.example.maple.Service;

import com.example.maple.DTO.MemberDTO;
import com.example.maple.Entity.Member;
import com.example.maple.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    public List<Member> index() {
        return memberRepository.findAll();
    }

    public Member create(MemberDTO memberDTO) {
        Member member = memberDTO.toMemberEntity(memberDTO);
        memberRepository.save(member);
        return member;
    }

    public Member update(String mid, MemberDTO memberDTO) {
        Member member = memberDTO.toupdateEntity(memberDTO);
        Member target = memberRepository.findById(mid).orElse(null);
        target.patch(member);
        memberRepository.save(target);
        return target;
    }

    public Member delete(String mid) {
        Member target = memberRepository.findById(mid).orElse(null);
        memberRepository.delete(target);
        return target;
    }
}
