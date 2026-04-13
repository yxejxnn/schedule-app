package com.example.ch3scheduleapp.repository;

import com.example.ch3scheduleapp.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    long countByscheduleId(Long scheduleId);
    List<Comment> findByScheduleId(Long scheduleId);
}
