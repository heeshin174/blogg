package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserViewController {
    @GetMapping("/login")
    public String login() {
        return "login"; // login.html 불러오기
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup"; // signup.html 불러오기
    }
}
