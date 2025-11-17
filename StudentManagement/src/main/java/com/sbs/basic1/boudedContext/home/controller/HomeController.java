package com.sbs.basic1.boudedContext.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // 스프링부트한테 이 클래스가 컨트롤러임을 알려줌
public class HomeController {
  @GetMapping("/home/main") // /home/main 주소로 GET 요청이 오면 이 메서드가 실행됨
  @ResponseBody // 이 메서드의 실행된 결과를 body에 담아서 응답으로 보냄
  public String showMain() {
    return "안녕하세요, 홈 페이지입니다.";
  }
}
