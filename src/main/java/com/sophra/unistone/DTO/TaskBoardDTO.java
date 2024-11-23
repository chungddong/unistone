package com.sophra.unistone.DTO;

import com.sophra.unistone.Entity.TaskBoard;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TaskBoardDTO {

    private TaskBoard taskBoard; // 기존 TaskBoard 데이터

    private Long projectId; // 추가 전달 작업보드 분류 ID
    
    // 사용자 추가 코드 필요할수도
}
