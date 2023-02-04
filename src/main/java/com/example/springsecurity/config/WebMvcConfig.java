package com.example.springsecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

/**
자바 기반의 스프링 MVC 환경을 설정하여 콜백 방법을 정의 할 수 있습니다.
 {@EnableWebMvc} 통해 MVC 사용자 정의값을 불러올 수 있습니다.
@author : 고의동
@since  : 1.0
*/
@Configuration // IOC에 등록하기 위한 어노테이션
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * WebMvcConfigurer 인터페이스내에 리졸버를 구현하고 등록 합니다.
     * @param registry 등록 클래스
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        // 타임리프 리졸버 설정
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        // 타임리프 컨텐츠 타입을 설정
        resolver.setContentType("text/html; charset=UTF-8");
        // 타임리프 리졸버를 등록
        registry.viewResolver(resolver);
    }
}
