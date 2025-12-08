package com.sbs.qnaService.boudedContext.schedule.service;

import com.sbs.qnaService.boudedContext.schedule.entity.Schedule;
import com.sbs.qnaService.boudedContext.schedule.form.ScheduleForm;
import com.sbs.qnaService.boudedContext.schedule.repository.ScheduleRepository;
import com.sbs.qnaService.boudedContext.user.entity.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    /**
     * 스케줄 생성
     */
    public void create(ScheduleForm scheduleForm, SiteUser siteUser) {
        Schedule schedule = new Schedule();
        schedule.setDate(scheduleForm.getDate());
        schedule.setDayOfWeek(scheduleForm.getDayOfWeek());
        schedule.setSubject(scheduleForm.getSubject());
        schedule.setStartTime(scheduleForm.getStartTime());
        schedule.setEndTime(scheduleForm.getEndTime());
        schedule.setRoom(scheduleForm.getRoom());
        schedule.setMemo(scheduleForm.getMemo());
        schedule.setAuthor(siteUser);

        scheduleRepository.save(schedule);
    }

    /**
     * 스케줄 목록 + 검색 + 페이징
     * 검색은 과목명(subject) + 메모(memo)에 대해 like 검색
     */
    public Page<Schedule> getList(int page, String kw) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Order.desc("date"), Sort.Order.desc("startTime")));

        if (kw == null || kw.trim().isEmpty()) {
            return scheduleRepository.findAll(pageable);
        } else {
            return scheduleRepository.findBySubjectContainingOrMemoContaining(kw, kw, pageable);
        }
    }

    /**
     * 단일 스케줄 조회
     */
    public Schedule getSchedule(Long id) {
        Optional<Schedule> opSchedule = scheduleRepository.findById(id);
        return opSchedule.orElseThrow(() -> new IllegalArgumentException("해당 스케줄을 찾을 수 없습니다. id=" + id));
    }

    /**
     * 스케줄 수정
     */
    public void modify(Schedule schedule, ScheduleForm form) {
        schedule.setDate(form.getDate());
        schedule.setDayOfWeek(form.getDayOfWeek());
        schedule.setSubject(form.getSubject());
        schedule.setStartTime(form.getStartTime());
        schedule.setEndTime(form.getEndTime());
        schedule.setRoom(form.getRoom());
        schedule.setMemo(form.getMemo());

        scheduleRepository.save(schedule);
    }

    /**
     * 스케줄 삭제
     */
    public void delete(Schedule schedule) {
        scheduleRepository.delete(schedule);
    }
}
