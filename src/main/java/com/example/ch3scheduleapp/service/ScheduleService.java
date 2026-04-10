package com.example.ch3scheduleapp.service;

import com.example.ch3scheduleapp.dto.ScheduleCreateRequestDto;
import com.example.ch3scheduleapp.dto.ScheduleCreateResponseDto;
import com.example.ch3scheduleapp.entity.Schedule;
import com.example.ch3scheduleapp.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
