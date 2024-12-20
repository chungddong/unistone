package com.sophra.unistone.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "TaskBoard")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TaskBoard {

    @Id
    @GeneratedValue
    private Long id;
    private String taskName;
    private String taskContent;
    private Date startDate;
    private Date endDate;

    //작업보드 분류 식별자
    @ManyToOne
    @JoinColumn(name = "taskBoardStatus_id", nullable = false)
    private TaskBoardStatus taskBoardStatus;

}
