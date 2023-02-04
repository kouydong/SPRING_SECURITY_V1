package com.example.springsecurity.config.auth;
// 시큐리티가 /login 주소 요청이 오면 낚아채서 로그인을 진행
// 로그인 진행이 완료가 되면 시큐리티 session 만들어 줌(Security ContextHolder 에 세션 정보를 저장함
// 시큐리터에 들어갈수 있는 객체는 Authentication 타입의 객체만 들어갈 수 있다.
// Authentication 안에 User 정보가 있어야 됩니다.
// User 객체의 타입은 UserDetails 타입의 객체 여야 합니다.

// Security Session => Authentication => UserDetail(PrincipalDetails) 타입이어야 한다.

import com.example.springsecurity.domain.entity.UserTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class PrincipalDetails implements UserDetails {

    private UserTest userTest;

    public PrincipalDetails(UserTest user) {
        this.userTest = user;
    }

    // 해당 User 권한 리턴
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 컬렉션 객체를
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return userTest.getRole();
            }
        });
        return collection;
    }

    @Override
    public String getPassword() {
        return userTest.getPassword();
    }

    @Override
    public String getUsername() {
        return userTest.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
