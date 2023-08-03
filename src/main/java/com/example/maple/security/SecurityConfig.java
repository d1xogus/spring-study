package com.example.maple.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import springfox.documentation.swagger.web.SecurityConfiguration;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity       //spring security 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

//    @Bean
//    @Override
//    // AuthenticationManager는 인증 요청을 처리하는 데 필요
//    public AuthenticationManager authenticationManagerBean() throws Exception{
//        return super.authenticationManagerBean();
//    }
//
    @Override
    // 다른 엔드포인트에 대한 보안 설정을 구성
    protected void configure(HttpSecurity httpSecurity) throws Exception{

        httpSecurity.httpBasic().disable()      //HTTP 기본 인증을 비활성화
                .csrf().disable()      //CSRF(Cross-Site Request Forgery) 보호를 비활성화
                .authorizeHttpRequests()    //요청에 대한 권한을 설정
                .antMatchers("/test").authenticated()       //"/test" 엔드포인트는 인증된 사용자만 접근할 수 있다
                .antMatchers("/admin").hasRole("ADMIN")     //"/admin" 엔드포인트는 "ADMIN" 역할을 가진 사용자만 접근할 수 있다
                .antMatchers("/user").hasRole("MEMBER")     //"/user" 엔드포인트는 "MEMBER" 역할을 가진 사용자만 접근할 수 있다
                .antMatchers("/**").permitAll()         // 다른 모든 엔드포인트는 모든 사용자에게 허용
                .and()
                // 커스텀한 JwtAuthenticationFilter를 UsernamePasswordAuthenticationFilter 이전에 등록하여 JWT 인증을 처리
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);        //세션을 생성하지 않고, 상태를 유지하지 않는 세션 정책을 설정
    }

    @Override
    public void configure(WebSecurity webSecurity){
        webSecurity.ignoring().antMatchers("/v2/api-docs", "/swagger-resources/**", "/swagger-ui.html",
                "/webjars/**", "/swagger/**", "/sign-api/exception");
    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .httpBasic().disable()
//                .csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeRequests()
//                .antMatchers("/members/login").permitAll()
//                .antMatchers("/members/test").hasRole("USER")
//                .anyRequest().authenticated()
//                .and()
//                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
//        return http.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }
}
