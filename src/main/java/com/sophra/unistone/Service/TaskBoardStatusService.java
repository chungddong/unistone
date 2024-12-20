package com.sophra.unistone.Service;

import com.sophra.unistone.Entity.TaskBoardStatus;
import com.sophra.unistone.Repository.TaskBoardStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskBoardStatusService {

    @Autowired
    private TaskBoardStatusRepository taskBoardStatusRepository;


    /**
     * 모든 작업보드 분류 반환
     *
     * @param projectId 프로젝트 id
     * @return 프로젝트의 모든 작업분류 반환
     */
    public List<TaskBoardStatus> getTaskBoardStatusByProjectId(Long projectId) {
        return taskBoardStatusRepository.findByProject_Id(projectId);
    }
}
