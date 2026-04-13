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
        List<Schedule> filteredScheduleList = new ArrayList<>();

        // 작성자명이 들어오지 않으면, 모든 일정을 다 넣기
        if (authorName == null || authorName.isBlank()) {
            filteredScheduleList.addAll(scheduleList);
        } else {
            // 작성자명이 들어오면 같은 작성자명의 일정만 넣기
            for (Schedule schedule : scheduleList) {
                if (schedule.getAuthorName().equals(authorName)) {
                    filteredScheduleList.add(schedule);
                }
            }
        }
        // 수정일 기준 내림차순 정렬
        filteredScheduleList.sort((s1, s2) -> s2.getUpdatedAt().compareTo(s1.getUpdatedAt()));

        List<ScheduleGetAllResponseDto> dtos = new ArrayList<>();

        for (Schedule schedule : filteredScheduleList) {
            ScheduleGetAllResponseDto dto = new ScheduleGetAllResponseDto(
                    schedule.getId(),
                    schedule.getTitle(),
                    schedule.getContent(),
                    schedule.getAuthorName(),
                    schedule.getCreatedAt(),
                    schedule.getUpdatedAt()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    // 선택 조회
    @Transactional(readOnly = true)
    public ScheduleGetOneResponseDto getOne(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("없는 일정입니다.")
        );

        List<Comment> commentList = commentRepository.findByScheduleId(scheduleId);

        // 댓글을 DTO로 변환
        List<CommentCreateResponseDto> dtos = new ArrayList<>();

        for (Comment comment : commentList) {
            CommentCreateResponseDto dto = new CommentCreateResponseDto(
                    comment.getId(),
                    comment.getContent(),
                    comment.getAuthorName(),
                    comment.getCreatedAt(),
                    comment.getUpdatedAt()
            );
            dtos.add(dto);
        }

        return new ScheduleGetOneResponseDto(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getAuthorName(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt(),
                dtos
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
            throw new IllegalArgumentException("비밀번호가 일치하기 않습니다.");
        }
        // 비밀번호가 일치 할 때
        // 삭제
        scheduleRepository.delete(schedule);
    }
}
