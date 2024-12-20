package com.sophra.unistone.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ScrapChat")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ScrapChat {

    @Id
    @GeneratedValue
    private Long id; // 단일 기본 키를 사용

    @ManyToOne
    @JoinColumn(name = "scrap_id", nullable = false)
    private Scrap scrap; // Scrap 관계 정의

    @ManyToOne
    @JoinColumn(name = "chat_id", nullable = false)
    private Chat chat; // Chat 관계 정의

}
