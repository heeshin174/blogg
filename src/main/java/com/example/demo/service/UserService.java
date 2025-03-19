package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.dto.AddUserRequest;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(AddUserRequest request) {
        return userRepository.save(User.builder()
                .email(request.getEmail())
                // password 암호화
                .password(bCryptPasswordEncoder.encode(request.getPassword()))
                .build()).getId();
    }

}
