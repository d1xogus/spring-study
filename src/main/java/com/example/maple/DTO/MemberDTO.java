package com.example.maple.DTO;

import com.example.maple.Entity.Equipment;
import com.example.maple.Entity.Member;
import lombok.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class MemberDTO {

    // 사용자 이메일
    private String mEmail;

    // 사용자 비밀번호
    private String mPasswd;

    // 사용자 이름
    private String mName;

    private String minfo;
    public static Member toMemberEntity(MemberDTO memberDTO) {
//        Member member = new Member();
//        member.setMPasswd(memberDTO.getMPasswd());
//        member.setMEmail(memberDTO.getMEmail());
//        member.setMName(memberDTO.getMName());
//        member.setMinfo(memberDTO.getMinfo());
//        member.setEquipmentList(memberDTO.getEquipmentList());

        Member newMember = Member.builder()
                .mName(memberDTO.getMName())
                .mEmail(memberDTO.getMEmail())
                .minfo(memberDTO.getMinfo())
                .mPasswd(memberDTO.getMPasswd())
                .build();
        return newMember;
    }

    public static Member toupdateEntity(MemberDTO memberDTO) {
        Member member = new Member();
        member.setMPasswd(memberDTO.getMPasswd());
        member.setMEmail(memberDTO.getMEmail());
        member.setMName(memberDTO.getMName());
        member.setMinfo(memberDTO.getMinfo());
        return member;

//        Member member = Member.builder()
//                .mName(memberDTO.getMName())
//                .mEmail(memberDTO.getMEmail())
//                .minfo(memberDTO.getMinfo())
//                .mPasswd(memberDTO.getMPasswd())
//                .build();
//        return member;
    }
}
