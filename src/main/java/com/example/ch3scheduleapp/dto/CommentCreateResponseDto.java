package com.example.ch3scheduleapp.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentCreateResponseDto {

    // 속성
    private final Long id;
    private final String content;
    private final String authorName;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    // 생성자
    public CommentCreateResponseDto(Long id, String content, String authorName, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.content = content;
        this.authorName = authorName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
