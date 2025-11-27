package com.sbs.basic1.boudedContext.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    // 메인 홈페이지
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "학생 출결관리 시스템");
        return "index"; // index.html
    }

    // 로그인 페이지
    @GetMapping("/login")
    public String loginPage() {
        return "login"; // login.html
    }

    // 로그인 처리
    @PostMapping("/login")
    public String loginSubmit(String username, String password, Model model) {
        System.out.println("로그인 요청: " + username + " / " + password);

        // 나중에 DB 로그인 검증 추가
        model.addAttribute("message", "아직 로그인 기능은 구현 전입니다.");

        return "login";
    }
}
