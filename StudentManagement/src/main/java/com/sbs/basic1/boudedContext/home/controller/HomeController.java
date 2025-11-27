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


    }
