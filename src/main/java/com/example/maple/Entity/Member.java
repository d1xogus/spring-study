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
    private Long mid;

    // 사용자 이메일
    private String mEmail;

    // 사용자 비밀번호
    private String mPasswd;

    // 사용자 이름
    private String mName;

    // 판매/구매 목록
    @Builder.Default //객체를 생성할 때 equipmentList 필드에 기본값인 빈 리스트(ArrayList)를 할당하기 위해서입니다.
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL) // 어노테이선 관계 설정, 주인엔티티의 필드 설정
    private List<Equipment> equipmentList = new ArrayList<>();

    // 사용자 정보
    private String minfo;

    public void patch(Member member){
        if(member.mEmail != null){
            this.mEmail = member.mEmail;
        }
        if(member.minfo != null){
            this.minfo = member.minfo;
        }
        if(member.mName != null){
            this.mName = member.mName;
        }
        if(member.mPasswd != null){
            this.mPasswd = member.mPasswd;
        }
    }
}
