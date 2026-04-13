package com.example.ch3scheduleapp.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleGetAllResponseDto {

    // 속성
    private final Long id;
    private final String title;
    private final String content;
    private final String authorName;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    // 생성자
    public ScheduleGetAllResponseDto(Long id, String title, String content, String authorName, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.authorName = authorName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
