package com.sophra.unistone.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

    //작업보드 리스트 식별자

}
