package com.example.maple.Entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "member_tb")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member extends Time{

    // 사용자 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long m_id;

    // 사용자 이메일
    private String m_Email;

    // 사용자 비밀번호
    private String m_Passwd;

    // 사용자 이름
    private String m_Name;

    // 판매/구매 목록
    @Builder.Default //객체를 생성할 때 equipmentList 필드에 기본값인 빈 리스트(ArrayList)를 할당하기 위해서입니다.
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL) // 어노테이선 관계 설정, 주인엔티티의 필드 설정
    private List<Equipment> equipmentList = new ArrayList<>();

    // 사용자 정보
    private String m_info;

    public void patch(Member member){
        if(member.m_Email != null){
            this.m_Email = member.m_Email;
        }
        if(member.m_info != null){
            this.m_info = member.m_info;
        }
        if(member.m_Name != null){
            this.m_Name = member.m_Name;
        }
        if(member.m_Passwd != null){
            this.m_Passwd = member.m_Passwd;
        }
    }
}
