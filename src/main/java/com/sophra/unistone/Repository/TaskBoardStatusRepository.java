package com.sophra.unistone.Repository;

import com.sophra.unistone.Entity.ProjectUser;
import com.sophra.unistone.Entity.TaskBoardStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskBoardStatusRepository extends JpaRepository<TaskBoardStatus, Long>{

    // 특정 project_id로 TaskBoardStatus 조회
    List<TaskBoardStatus> findByProject_Id(Long projectId);

}
