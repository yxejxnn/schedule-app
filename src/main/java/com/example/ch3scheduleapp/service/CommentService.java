package com.example.ch3scheduleapp.service;

import com.example.ch3scheduleapp.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    // 속성
    private final CommentRepository commentRepository;
}
