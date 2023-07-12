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
    private Long m_id;

    // 사용자 이메일
    private String m_Email;

    // 사용자 비밀번호
    private String m_Passwd;

    // 사용자 이름
    private String m_Name;

    //private List<Equipment> equipmentList;
    private String m_info;
    public static Member toMemberEntity(MemberDTO memberDTO) {
        Member member = new Member();
        member.setM_id(memberDTO.getM_id());
        member.setM_Passwd(memberDTO.getM_Passwd());
        member.setM_Email(memberDTO.getM_Email());
        member.setM_Name(memberDTO.getM_Name());
        member.setM_info(memberDTO.getM_info());
        return member;
    }

    public static Member toupdateEntity(MemberDTO memberDTO) {
        Member member = new Member();
        member.setM_Passwd(memberDTO.getM_Passwd());
        member.setM_Email(memberDTO.getM_Email());
        member.setM_Name(memberDTO.getM_Name());
        member.setM_info(memberDTO.getM_info());
        return member;
    }
}
