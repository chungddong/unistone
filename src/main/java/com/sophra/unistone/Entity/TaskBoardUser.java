package com.sophra.unistone.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.catalina.User;

@Entity
@Table(name = "TaskBoardUser")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TaskBoardUser { //작업보드 사용자

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user; // Users 관계 정의

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project; // Project 관계 정의


}
