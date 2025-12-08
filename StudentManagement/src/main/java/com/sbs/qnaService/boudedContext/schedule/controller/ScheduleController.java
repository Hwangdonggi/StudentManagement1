package com.sbs.qnaService.boudedContext.schedule.controller;

import com.sbs.qnaService.boudedContext.schedule.entity.Schedule;
import com.sbs.qnaService.boudedContext.schedule.form.ScheduleForm;
import com.sbs.qnaService.boudedContext.schedule.service.ScheduleService;
import com.sbs.qnaService.boudedContext.user.entity.SiteUser;
import com.sbs.qnaService.boudedContext.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@RequestMapping("/schedule")
@RequiredArgsConstructor
@Controller
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final UserService userService;

    /**
     * 스케줄 리스트 + 검색 + 페이징
     * /schedule/list?page=0&kw=자바
     */
    @GetMapping("/list")
    public String list(Model model,
                       @RequestParam(value = "page", defaultValue = "0") int page,
                       @RequestParam(value = "kw", defaultValue = "") String kw) {

        Page<Schedule> paging = scheduleService.getList(page, kw);

        model.addAttribute("paging", paging);
        model.addAttribute("kw", kw);

        return "schedule_list";
    }

    /**
     * 스케줄 등록 폼
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String create(ScheduleForm scheduleForm) {
        return "schedule_form";
    }

    /**
     * 스케줄 등록 처리
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String create(@Valid ScheduleForm scheduleForm,
                         BindingResult bindingResult,
                         Principal principal) {

        if (bindingResult.hasErrors()) {
            return "schedule_form";
        }

        SiteUser siteUser = userService.getUser(principal.getName());
        scheduleService.create(scheduleForm, siteUser);

        return "redirect:/schedule/list";
    }

    /**
     * 스케줄 수정 폼
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String modify(@PathVariable("id") Long id,
                         ScheduleForm scheduleForm,
                         Principal principal) {

        Schedule schedule = scheduleService.getSchedule(id);

        // 작성자 체크
        if (!schedule.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }

        // 기존 값 폼에 채워 넣기
        scheduleForm.setDate(schedule.getDate());
        scheduleForm.setDayOfWeek(schedule.getDayOfWeek());
        scheduleForm.setSubject(schedule.getSubject());
        scheduleForm.setStartTime(schedule.getStartTime());
        scheduleForm.setEndTime(schedule.getEndTime());
        scheduleForm.setRoom(schedule.getRoom());
        scheduleForm.setMemo(schedule.getMemo());

        return "schedule_form";
    }

    /**
     * 스케줄 수정 처리
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String modify(@PathVariable("id") Long id,
                         @Valid ScheduleForm scheduleForm,
                         BindingResult bindingResult,
                         Principal principal) {

        if (bindingResult.hasErrors()) {
            return "schedule_form";
        }

        Schedule schedule = scheduleService.getSchedule(id);

        if (!schedule.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }

        scheduleService.modify(schedule, scheduleForm);

        return "redirect:/schedule/list";
    }

    /**
     * 스케줄 삭제
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id,
                         Principal principal) {

        Schedule schedule = scheduleService.getSchedule(id);

        if (!schedule.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }

        scheduleService.delete(schedule);

        return "redirect:/schedule/list";
    }
}
