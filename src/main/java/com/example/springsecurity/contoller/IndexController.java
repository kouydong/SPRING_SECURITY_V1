package com.example.springsecurity.contoller;

import com.example.springsecurity.domain.entity.UserTest;
import com.example.springsecurity.domain.repository.UserTestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

// view를 리턴한다.
@Controller
@Slf4j
@RequiredArgsConstructor
public class IndexController {

    private final UserTestRepository userTestRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping({"","/"})
    public String index() {
        return "index";
    }

    @GetMapping("/user")
    public @ResponseBody String user() {
        return "user";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/manager")
    public String manager() {
        return "manager";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "joinForm";
    }

    // MediaType produce 및 consume 에 대해서 이해 필요
    @PostMapping(value = "/join", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String join(UserTest userTest) {
        log.info(">> 사용자 가입 프로세스{}", userTest);
        userTest.setPassword(bCryptPasswordEncoder.encode(userTest.getPassword()));
        userTest.setRole("ROLE_USER");
        userTestRepository.save(userTest);
        return "redirect:/loginForm";
    }

    @GetMapping("/info")
    public @ResponseBody String info() {
        return "개인정보";
    }
}