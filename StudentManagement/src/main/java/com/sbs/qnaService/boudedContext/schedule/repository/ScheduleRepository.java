package com.sbs.qnaService.boudedContext.schedule.repository;

import com.sbs.qnaService.boudedContext.schedule.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Page<Schedule> findBySubjectContainingOrMemoContaining(String kw, String kw1, Pageable pageable);
}
