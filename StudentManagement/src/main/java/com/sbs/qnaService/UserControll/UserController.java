package com.sbs.qnaService.UserControll;

import com.sbs.basic1.boudedContext.user.service.UserService;
import com.sbs.basic1.boudedContext.user.form.UserCreateForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 회원가입 폼 페이지
     * GET /user/signup
     */
    @GetMapping("/user/signup")
    public String signup(UserCreateForm userCreateForm) {
        // 비어있는 폼 객체를 뷰에 넘김 (타임리프에서 th:object 로 사용)
        return "user_signup"; // user_signup.html
    }

    /**
     * 회원가입 처리
     * POST /user/signup
     */
    @PostMapping("/user/signup")
    public String signupSubmit(@Valid UserCreateForm userCreateForm,
                               BindingResult bindingResult,
                               Model model) {

        // 1) 폼 검증 에러가 있으면 다시 회원가입 폼으로
        if (bindingResult.hasErrors()) {
            return "user_signup";
        }

        // 2) 비밀번호 일치 확인
        if (!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
            bindingResult.rejectValue(
                    "password2",
                    "passwordInCorrect",
                    "2개의 비밀번호가 일치하지 않습니다."
            );
            return "user_signup";
        }

        try {
            // 3) 실제 회원 생성 (서비스에서 비밀번호 암호화 + 저장)
            userService.create(
                    userCreateForm.getUsername(),
                    userCreateForm.getPassword1(),
                    userCreateForm.getEmail()
            );
        } catch (DataIntegrityViolationException e) {
            // 중복 회원 등 제약조건 충돌
            e.printStackTrace();
            bindingResult.reject(
                    "signupFailed",
                    "이미 사용 중인 아이디 또는 이메일입니다."
            );
            return "user_signup";
        } catch (Exception e) {
            // 그 외 예외
            e.printStackTrace();
            bindingResult.reject(
                    "signupFailed",
                    "회원가입 처리 중 오류가 발생했습니다."
            );
            return "user_signup";
        }

        // 4) 회원가입 완료 후 로그인 페이지로 이동
        return "redirect:/login";
    }
}