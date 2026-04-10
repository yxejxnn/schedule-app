package com.example.ch3scheduleapp.controller;

import com.example.ch3scheduleapp.dto.ScheduleCreateRequestDto;
import com.example.ch3scheduleapp.dto.ScheduleCreateResponseDto;
import com.example.ch3scheduleapp.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 생성
    @PostMapping("/schedules")
    public ResponseEntity<ScheduleCreateResponseDto> scheduleCreate(@RequestBody ScheduleCreateRequestDto requestDto) {
        ScheduleCreateResponseDto result = scheduleService.save(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
