package com.example.maple.Service;

import com.example.maple.DTO.MemberDTO;
import com.example.maple.Entity.Member;
import com.example.maple.Repository.MemberRepository;
import com.example.maple.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    public final JwtTokenProvider jwtTokenProvider;
    public final PasswordEncoder passwordEncoder;
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
//    private final AuthenticationManagerBuilder authenticationManagerBuilder;
//
//    @Transactional
//    public TokenDTO login(String memberId, String password) {
//        // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
//        // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(memberId, password);
//
//        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
//        // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
//        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
//
//        // 3. 인증 정보를 기반으로 JWT 토큰 생성
//        TokenDTO tokenDTO = jwtTokenProvider.generateToken(authentication);
//
//        return tokenDTO;
//    }
}
