package com.example.ch3scheduleapp.repository;

import com.example.ch3scheduleapp.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
