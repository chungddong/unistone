package com.sophra.unistone.Service;

import com.sophra.unistone.Entity.*;
import com.sophra.unistone.Repository.TaskBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskBoardService {

    @Autowired
    private TaskBoardRepository taskBoardRepository;

    /**
     * 모든 작업보드 반환
     * @param taskBoardStatusId 작업보드 분류 id
     * @return 작업보드 분류의 모든 작업보드
     */
    public List<TaskBoard> getTaskBoardsByStatusId(Long taskBoardStatusId) {
        return taskBoardRepository.findByTaskBoardStatusId(taskBoardStatusId);
    }

}
