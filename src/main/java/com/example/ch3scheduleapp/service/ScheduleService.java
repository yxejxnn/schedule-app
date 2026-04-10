package com.example.ch3scheduleapp.service;

import com.example.ch3scheduleapp.dto.ScheduleCreateRequestDto;
import com.example.ch3scheduleapp.dto.ScheduleCreateResponseDto;
import com.example.ch3scheduleapp.dto.ScheduleGetResponseDto;
import com.example.ch3scheduleapp.entity.Schedule;
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
    public List<ScheduleGetResponseDto> getAll(String authorName) {
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

        List<ScheduleGetResponseDto> dtos = new ArrayList<>();

        for (Schedule schedule : filteredScheduleList) {
            ScheduleGetResponseDto dto = new ScheduleGetResponseDto(
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
    public ScheduleGetResponseDto getOne(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("없는 일정입니다.")
        );
        return new ScheduleGetResponseDto(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getAuthorName(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );
    }
}
