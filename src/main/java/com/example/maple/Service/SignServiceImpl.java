package com.example.maple.Service;

import com.example.maple.DTO.SignInResultDTO;
import com.example.maple.Entity.Member;
import com.example.maple.Repository.MemberRepository;
import com.example.maple.controller.SignController;
import com.example.maple.security.CommonResponse;
import com.example.maple.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.hibernate.mapping.Collection;
import org.modelmapper.Converters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.maple.DTO.SignUpResultDTO;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class SignServiceImpl implements SignService{
    private final Logger LOGGER = LoggerFactory.getLogger(SignServiceImpl.class);
    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public SignUpResultDTO signUp(String id, String pw, String name, String role){
        Member member;
        if (role.equalsIgnoreCase("admin")){
            member = Member.builder()
                    .loginId(id)
                    .mPasswd(passwordEncoder.encode(pw))
                    .mName(name)
                    .roles(Collections.singletonList("ROLE_ADMIN"))
                    .build();
        } else{
            member =Member.builder()
                    .loginId(id)
                    .mPasswd(passwordEncoder.encode(pw))
                    .mName(name)
                    .roles(Collections.singletonList("ROLE_USER"))
                    .build();
        }

        Member savedMember = memberRepository.save(member);
        SignUpResultDTO signUpResultDTO = new SignInResultDTO();

        if (!savedMember.getMName().isEmpty()){
            setSuccessResult(signUpResultDTO);
        } else {
            setFailResult(signUpResultDTO);
        }
        return signUpResultDTO;
    }

    @Override
    public SignInResultDTO signIn(String id, String pw) throws RuntimeException{
        Member member = memberRepository.findByLoginId(id);
        LOGGER.info("[getSignInResult] id : {}", id);
        LOGGER.info("[getSignInResult] 패스워드 비교 수행");
        if (!passwordEncoder.matches(pw, member.getPassword())){
            throw new RuntimeException();
        }
        LOGGER.info("[getSignInResult] 패스워드 일치");
        LOGGER.info("[getSignInResult] SignInResultDTO 객체 생성");
        SignInResultDTO signInResultDTO = SignInResultDTO.builder()
                .token(jwtTokenProvider.createToken(String.valueOf(member.getLoginId()), member.getRoles()))
                .build();
        LOGGER.info("[getSignInResult] 객체에 값 주입");
        setSuccessResult(signInResultDTO);
        return signInResultDTO;
    }

    private void setSuccessResult(SignUpResultDTO result){
        result.setSuccess(true);
        result.setCode(CommonResponse.SUCCESS.getCode());
        result.setMsg(CommonResponse.SUCCESS.getMsg());
    }

    private void setFailResult(SignUpResultDTO result){
        result.setSuccess(false);
        result.setCode(CommonResponse.FAIL.getCode());
        result.setMsg(CommonResponse.FAIL.getMsg());
    }
}
