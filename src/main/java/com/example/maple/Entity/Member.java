package com.example.maple.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.util.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Table(name = "member_tb")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member implements UserDetails {

    // 사용자 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 자동 생성
    private long mid;

    //
    @Column(nullable = false, unique = true)
    private String loginId;

    // 사용자 이메일
    private String mEmail;

    // 사용자 비밀번호
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false)
    private String mPasswd;

    // 사용자 이름
    private String mName;

    // 판매/구매 목록
    @Builder.Default //객체를 생성할 때 equipmentList 필드에 기본값인 빈 리스트(ArrayList)를 할당하기 위해서입니다.
    @OneToMany(fetch = FetchType.LAZY) //(mappedBy = "member", cascade = CascadeType.ALL)
    @JoinColumn(name = "mem")
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


    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public String getUsername() {
        return loginId;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public String getPassword() {
        return mPasswd;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isEnabled() {
        return true;
    }

}
