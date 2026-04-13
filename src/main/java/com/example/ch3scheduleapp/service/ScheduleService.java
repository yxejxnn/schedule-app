package com.example.ch3scheduleapp.service;

import com.example.ch3scheduleapp.dto.*;
import com.example.ch3scheduleapp.entity.Comment;
import com.example.ch3scheduleapp.entity.Schedule;
import com.example.ch3scheduleapp.repository.CommentRepository;
import com.example.ch3scheduleapp.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final CommentRepository commentRepository;

    // 저장
    @Transactional
    public ScheduleCreateResponseDto save(ScheduleCreateRequestDto requestDto) {
        Schedule schedule = new Schedule(
                requestDto.getTitle(),
                requestDto.getContent(),
                requestDto.getAuthorName(),
                requestDto.getPassword()
        );
        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new ScheduleCreateResponseDto(
                savedSchedule.getId(),
                savedSchedule.getTitle(),
                savedSchedule.getContent(),
                savedSchedule.getAuthorName(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getUpdatedAt()
        );
    }

    // 전체 조회
    @Transactional(readOnly = true)
    public List<ScheduleGetAllResponseDto> getAll(String authorName) {
        List<Schedule> scheduleList = scheduleRepository.findAll();

        // 작성자명(authorName) 조건이 맞는 일정만 담을 리스트 생성
        List<Schedule> filteredScheduleList = scheduleList.stream()
                .filter(schedule -> authorName == null || authorName.isBlank() || schedule.getAuthorName().equals(authorName))
                .sorted((s1, s2) -> s2.getUpdatedAt().compareTo(s1.getUpdatedAt()))
                .collect(Collectors.toList());

        return filteredScheduleList.stream()
                .map(schedule -> new ScheduleGetAllResponseDto(
                        schedule.getId(),
                        schedule.getTitle(),
                        schedule.getContent(),
                        schedule.getAuthorName(),
                        schedule.getCreatedAt(),
                        schedule.getUpdatedAt()
                ))
                .collect(Collectors.toList());
    }

    // 선택 조회
    @Transactional(readOnly = true)
    public ScheduleGetOneResponseDto getOne(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("없는 일정입니다.")
        );

        List<Comment> commentList = commentRepository.findByScheduleId(scheduleId);

        List<CommentCreateResponseDto> dtoList = commentList.stream()
                .map(comment -> new CommentCreateResponseDto(
                        comment.getId(),
                        comment.getContent(),
                        comment.getAuthorName(),
                        comment.getCreatedAt(),
                        comment.getUpdatedAt()
                ))
                .collect(Collectors.toList());

        return new ScheduleGetOneResponseDto(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getAuthorName(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt(),
                dtoList
        );
    }

    // 수정 (업데이트)
    @Transactional
    public ScheduleUpdateResponseDto update(Long scheduleId, ScheduleUpdateRequestDto requestDto) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("없는 일정입니다.")
        );

        // 비밀번호를 입력하지 않은 경우
        if (requestDto.getPassword() == null || requestDto.getPassword().isBlank()) {
            throw new IllegalArgumentException("비밀번호를 입력해주세요.");
        }

        // 비밀번호 검증
        if (!schedule.getPassword().equals(requestDto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        schedule.update(requestDto.getTitle(), requestDto.getAuthorName());

        return new ScheduleUpdateResponseDto(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getAuthorName(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );
    }

    // 삭제
    @Transactional
    public void delete(Long scheduleId, ScheduleDeleteRequestDto requestDto) {
        // 선택한 일정이 없는 경우
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("없는 일정입니다.")
        );
        // 선택한 일정이 있는 경우
        // 비밀번호 검증
        // 비밀번호를 입력하지 않은 경우
        if (requestDto.getPassword() == null || requestDto.getPassword().isBlank()) {
            throw new IllegalArgumentException("비밀번호를 입력해주세요.");
        }
        // 비밀번호가 일치하지 않을 때
        if (!schedule.getPassword().equals(requestDto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        // 비밀번호가 일치 할 때
        // 삭제
        scheduleRepository.delete(schedule);
    }
}
