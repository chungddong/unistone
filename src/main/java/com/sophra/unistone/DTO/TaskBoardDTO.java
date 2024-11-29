package com.sophra.unistone.DTO;

import com.sophra.unistone.Entity.TaskBoard;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class TaskBoardDTO {

    private TaskBoard taskBoard; // 기존 TaskBoard 데이터

    private Long projectId; // 추가 전달 작업보드 분류 ID

    private List<Long> userIds; //유저 아이디 목록
}
