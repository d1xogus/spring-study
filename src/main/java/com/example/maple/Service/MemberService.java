package com.example.maple.Service;

import com.example.maple.DTO.MemberDTO;
import com.example.maple.Entity.Member;
import com.example.maple.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    public Optional<Member> index(Long mid) {
        return memberRepository.findById(mid);
    }

    public Member create(MemberDTO memberDTO) {
        Member member = memberDTO.toMemberEntity(memberDTO);
        memberRepository.save(member);
        return member;
    }

    public Member update(Long mid, MemberDTO memberDTO) {
        Member member = memberDTO.toupdateEntity(memberDTO);
        Member target = memberRepository.findById(mid).orElseThrow(() -> new NoSuchElementException("그런 사용자 없다"));
        target.patch(member);
        memberRepository.save(target);
        return target;
    }

    public Member delete(Long mid) {
        Member target = memberRepository.findById(mid).orElse(null);
        memberRepository.delete(target);
        return target;
    }
}
