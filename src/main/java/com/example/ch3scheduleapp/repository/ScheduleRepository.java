package com.example.ch3scheduleapp.repository;

import com.example.ch3scheduleapp.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
