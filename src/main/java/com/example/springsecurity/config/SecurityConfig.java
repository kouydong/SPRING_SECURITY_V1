package com.example.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // IOC에 등록하기 위한 어노테이션
@EnableWebSecurity // WebSecurity 기능 활성화(스프링 시큐리티 필터가 스프링 필터체인에 등록)
@EnableMethodSecurity(securedEnabled = true) //  @secured 어노테이션 활성화 e.g @secured("ROLE_USER") 독립적인 메소드의 보안을 걸수 있음.
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable(); // csrf 빌활성화
        // 인증 요청이 URI /user 로 들어오면 인증 요청
        httpSecurity.authorizeHttpRequests()
                .requestMatchers("/user/**").authenticated() // 인증만 되면 아무나 들어갈 수 있음.
                // 인증 요청이 /manager 로 들어오면 ADMIN 및 MANAGER 역할만 접속 가능
                .requestMatchers("/manager/**").hasAuthority("ROLE_MANAGER")
                // 인증 요청이 /admin 로 들어오면 ADMIN 역할만 접속 가능
                .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
//                .requestMatchers("/admin/**").hasRole("ADMIN")
                // 그외 요청은 모두 수락
                .anyRequest().permitAll()
                .and()
                // 인증되지 않은 사용자의 경우 로그인 페이지로 이동
                .formLogin()
                // 사용자 정의 로긴 페이지로 이동
                .loginPage("/loginForm")
                // 객체를 식별할 수 있는 클라이언트에서 유저 아이디
                .usernameParameter("username")
                // 인증(Login) 주소가 호출이 되면 시큐리티가 대신 로그인을 진행
                .loginProcessingUrl("/login")
                // 인증이 성공하고 특정 페이지를 호출하지 않은 경우 디폴트 성공 페이지로 이동
                .defaultSuccessUrl("/");

        return httpSecurity.build();
    }

    // 해당 메서드의 리턴되는 오브젝트를 IoC 등록해줌
    @Bean
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }

}
