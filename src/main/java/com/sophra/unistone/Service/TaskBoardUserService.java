package com.sophra.unistone.Service;

import com.sophra.unistone.Entity.TaskBoardUser;
import com.sophra.unistone.Repository.TaskBoardUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskBoardUserService {

    @Autowired
    private TaskBoardUserRepository taskBoardUserRepository;

    public List<TaskBoardUser> getUsersByProjectId(Long projectId) {
        return taskBoardUserRepository.findByProjectId(projectId);
    }

}
