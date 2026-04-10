package com.example.ch3scheduleapp.dto;

import lombok.Getter;

@Getter
public class ScheduleCreateRequestDto {

    // 속성
    private String title;
    private String content;
    private String authorName;
    private String password;
}
