package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "id", updatable = false)
    private Long id;

    @Column( name = "title", nullable = false)
    private String title;

    @Column( name = "content", nullable = false)
    private String content;

    // 빌더 패턴 생성자
    @Builder
    public Article(String title, String content) {

        this.title = title;
        this.content = content;
    }
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @CreatedDate // Entity 생성 시간
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate // Entity 수정 시간
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
