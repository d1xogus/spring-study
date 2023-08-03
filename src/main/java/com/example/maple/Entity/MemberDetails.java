//package com.example.maple.Entity;
//
//import org.springframework.security.core.GrantedAuthority;
//
//import java.io.Serializable;
//import java.util.Collection;
//
//public interface MemberDetails extends Serializable {
//    // 계정의 권한목록을 반환
//    Collection<? extends GrantedAuthority> getAuthorities();
//    // 계정의 비밀번호를 반환
//    String getPassword();
//    // 계정의 이름/아이디를 반환
//    String getUsername();
//    // 계정이 만료됐는지 반환 , true가 만료되지 않음
//    boolean isAccountNonExpired();
//    // 계정이 잠겨있는지 반환, true가 잠기지 않았음
//    boolean isAccountNonLocked();
//    // 비밀번호가 만료됐는지 반환, true가 만료되지 않음
//    boolean isCredentialsNonExpired();
//    // 계정이 활성화됐는지 반환, true가 활성화
//    boolean isEnabled();
//
//}
