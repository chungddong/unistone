package com.sophra.unistone.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TaskBoardStatus")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TaskBoardStatus {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project; // Project와의 관계 정의

    private String statusName; // 작업 분류

    // TODO : 작업 상태 분류 순서 속성 필요할 수 있음


}
