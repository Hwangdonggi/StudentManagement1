package com.sbs.basic1.boudedContext.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReigsterController {

    // 회원가입 페이지
    @GetMapping("/register")
    public String registerPage() {
        return "register"; // register.html
    }

    // 회원가입 처리
    @PostMapping("/register")
    public String registerSubmit(String username, String password, String nickname, Model model) {
        System.out.println("회원가입 요청: " + username + " / " + password + " / " + nickname);

        // 나중에 DB 저장 기능 추가 예정
        model.addAttribute("message", "회원가입이 완료된 것처럼 보이지만 아직 DB는 연결 안됨 😆");

        return "register";
    }
}
