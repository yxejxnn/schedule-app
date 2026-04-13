package com.example.ch3scheduleapp.dto;

import lombok.Getter;

@Getter
public class CommentCreateRequestDto {

    // 속성
    private String content;
    private String authorName;
    private String password;
    private Long scheduleId;
}
