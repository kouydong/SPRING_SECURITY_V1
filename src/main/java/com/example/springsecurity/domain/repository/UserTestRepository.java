package com.example.springsecurity.domain.repository;

import com.example.springsecurity.domain.entity.UserTest;
import org.springframework.data.jpa.repository.JpaRepository;

// @Repository라는 어노테이션이 없어도 Ioc 처리
public interface UserTestRepository extends JpaRepository<UserTest, Long> {

    public UserTest findByUsername(String username);

}
