package com.example.ch3scheduleapp.controller;

import com.example.ch3scheduleapp.dto.*;
import com.example.ch3scheduleapp.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // 전체 조회
    @GetMapping("/schedules")
    public ResponseEntity<List<ScheduleGetAllResponseDto>> scheduleGetAll(@RequestParam(required = false) String authorName) {
        List<ScheduleGetAllResponseDto> result = scheduleService.getAll(authorName);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 선택 조회
    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<ScheduleGetOneResponseDto> scheduleGetOne(@PathVariable Long scheduleId) {
        ScheduleGetOneResponseDto result = scheduleService.getOne(scheduleId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 수정
    @PutMapping("/schedules/{scheduleId}")
    public ResponseEntity<ScheduleUpdateResponseDto> scheduleUpdate(@PathVariable Long scheduleId, @RequestBody ScheduleUpdateRequestDto requestDto) {
        ScheduleUpdateResponseDto result = scheduleService.update(scheduleId, requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 삭제
    @DeleteMapping("/schedules/{scheduleId}")
    public ResponseEntity<Void> scheduleDelete(@PathVariable Long scheduleId, @RequestBody ScheduleDeleteRequestDto requestDto) {
        scheduleService.delete(scheduleId, requestDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
