package com.example.ch3scheduleapp.controller;

import com.example.ch3scheduleapp.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {

    // 속성
    private final CommentService commentService;
}
