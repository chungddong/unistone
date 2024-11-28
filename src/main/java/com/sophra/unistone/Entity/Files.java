package com.sophra.unistone.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "Files")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Files {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private FileType fileType; // 파일 타입 (파일 또는 링크)

    private String title; // 자료 제목

    private String description; // 자료 설명

    private String filePath; // 파일 경로 (파일 시스템 경로 또는 웹페이지 링크)

    private LocalDateTime createdDate; // 등록일

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user; // 사용자와 연결

    public enum FileType {
        FILE, LINK
    }
}
