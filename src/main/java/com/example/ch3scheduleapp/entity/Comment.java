package com.example.ch3scheduleapp.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "comments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {

    // 속성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100)
    private String content;
    @Column(nullable = false)
    private String authorName;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private Long scheduleId;

    // 생성자
    public Comment(String content, String authorName, String password, Long scheduleId) {
        this.content = content;
        this.authorName = authorName;
        this.password = password;
        this.scheduleId = scheduleId;
    }
}
