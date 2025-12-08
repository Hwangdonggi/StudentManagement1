package com.sbs.qnaService.boudedContext.schedule.entity;

import com.sbs.qnaService.boudedContext.user.entity.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private String dayOfWeek;

    private String subject;

    private LocalTime startTime;

    private LocalTime endTime;

    private String room;

    private String memo;

    @ManyToOne
    private SiteUser author;
}
