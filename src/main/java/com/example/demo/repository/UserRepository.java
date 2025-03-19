package com.example.demo.repository;

import com.example.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // email로 사용자 정보 가저옴
    // Optional class는 null을 안 받기 위한 Java class
    Optional<User> findByEmail(String email);
}
