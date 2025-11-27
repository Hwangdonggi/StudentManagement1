package com.sbs.basic1.register.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    // 회원가입 페이지
    @GetMapping("/register")
    public String registerPage() {
        return "register"; // register.html
    }

    // 회원가입 처리
    @PostMapping("/register")
    public String registerSubmit(String nickname,
                                 String username,
                                 String password,
                                 Model model) {

        // TODO: 추후 DB 저장 로직 추가
        System.out.println("회원가입 요청 → 닉네임: " + nickname + ", 아이디: " + username);

        model.addAttribute("message", "아직 회원가입 기능은 구현 전입니다.");

        return "register";
    }
}