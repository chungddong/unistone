package com.sophra.unistone.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Scrap")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Scrap {

    @Id
    @GeneratedValue
    private Long id; // 단일 기본 키를 사용

    private String scrapName; // 스크랩 이름

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project; // Project와의 관계 정의

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user; // User와의 관계 정의

}
