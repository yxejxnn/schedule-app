package com.example.ch3scheduleapp.controller;

import com.example.ch3scheduleapp.dto.CommentCreateRequestDto;
import com.example.ch3scheduleapp.dto.CommentCreateResponseDto;
import com.example.ch3scheduleapp.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    // 속성
    private final CommentService commentService;

    // 생성
    @PostMapping
    public ResponseEntity<CommentCreateResponseDto> commentCreate(@RequestBody CommentCreateRequestDto requestDto) {
        CommentCreateResponseDto result = commentService.save(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
