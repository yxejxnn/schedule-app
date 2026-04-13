package com.example.ch3scheduleapp.dto;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ScheduleGetOneResponseDto {

    // 속성
    private final Long id;
    private final String title;
    private final String content;
    private final String authorName;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final List<CommentCreateResponseDto> commentList;

    // 생성자
    public ScheduleGetOneResponseDto(Long id, String title, String content, String authorName, LocalDateTime createdAt, LocalDateTime updatedAt, List<CommentCreateResponseDto> commentList) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.authorName = authorName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.commentList = commentList;
    }
}
