package com.example.ch3scheduleapp.service;

import com.example.ch3scheduleapp.dto.CommentCreateRequestDto;
import com.example.ch3scheduleapp.dto.CommentCreateResponseDto;
import com.example.ch3scheduleapp.entity.Comment;
import com.example.ch3scheduleapp.repository.CommentRepository;
import com.example.ch3scheduleapp.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    // 속성
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    // 생성
    @Transactional
    public CommentCreateResponseDto save(CommentCreateRequestDto requestDto) {
        // 일정 존재 확인
        scheduleRepository.findById(requestDto.getScheduleId()).orElseThrow(
                () -> new IllegalStateException("없는 일정입니다.")
        );

        // 댓글 10개 초과 시 예외처리
        long commentCount = commentRepository.countByScheduleId(requestDto.getScheduleId());
        if (commentCount >= 10) {
            throw new IllegalStateException("댓글은 10개까지만 작성할 수 있습니다.");
        }

        Comment comment = new Comment(
                requestDto.getContent(),
                requestDto.getAuthorName(),
                requestDto.getPassword(),
                requestDto.getScheduleId()
        );
        Comment savedComment = commentRepository.save(comment);

        return new CommentCreateResponseDto(
                savedComment.getId(),
                savedComment.getContent(),
                savedComment.getAuthorName(),
                savedComment.getCreatedAt(),
                savedComment.getUpdatedAt()
        );
    }
}
