package com.example.springsecurity.config.auth;

import com.example.springsecurity.domain.entity.UserTest;
import com.example.springsecurity.domain.repository.UserTestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// 시큐리티 설정에서 loginProcessingUrl("login")
// /login 요청이 오면 자동으로 UserDetailService 타입으로 IoC되어 있는 loadUserByUsername 함수가 실행

@Service
@RequiredArgsConstructor
@Slf4j
public class PrincipalDetailsService implements UserDetailsService {

    private final UserTestRepository userTestRepository;


    // IoC 컨테이너가 발동되고 클라이언트로 부터 username 을 가지고 옴
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserTest userTest = userTestRepository.findByUsername(username);
        log.info("유저테스트 {}", userTest);
        if(userTest != null) {
            return new PrincipalDetails(userTest);
        }
        return null;
    }
}
