package com.example.maple.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.util.Lazy;

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
public class Member{

    // 사용자 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 자동 생성
    private Long mid;

    // 사용자 이메일
    private String mEmail;

    // 사용자 비밀번호
    private String mPasswd;

    // 사용자 이름
    private String mName;

    // 판매/구매 목록
    @Builder.Default //객체를 생성할 때 equipmentList 필드에 기본값인 빈 리스트(ArrayList)를 할당하기 위해서입니다.
    @OneToMany(fetch = FetchType.EAGER) //(mappedBy = "member", cascade = CascadeType.ALL)
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
        if(member.equipmentList != null){
            this.equipmentList = member.equipmentList;
        }
    }
}
