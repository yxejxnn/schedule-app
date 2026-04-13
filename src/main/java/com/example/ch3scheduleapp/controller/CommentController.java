package com.example.ch3scheduleapp.controller;

import com.example.ch3scheduleapp.dto.CommentCreateRequestDto;
import com.example.ch3scheduleapp.dto.CommentCreateResponseDto;
import com.example.ch3scheduleapp.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {

    // 속성
    private final CommentService commentService;

    // 생성
    @PostMapping("/schedules/{scheduleId}/comments")
    public ResponseEntity<CommentCreateResponseDto> commentCreate(@PathVariable Long scheduleId, @RequestBody CommentCreateRequestDto requestDto) {
        CommentCreateResponseDto result = commentService.save(scheduleId, requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
