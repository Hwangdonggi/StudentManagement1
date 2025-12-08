package com.sbs.qnaService.boudedContext.schedule.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class ScheduleForm {

    @NotNull
    private LocalDate date;

    @NotBlank
    private String dayOfWeek;

    @NotBlank
    private String subject;

    @NotNull
    private LocalTime startTime;

    @NotNull
    private LocalTime endTime;

    private String room;

    private String memo;
}
