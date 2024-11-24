package com.sophra.unistone.Repository;

import com.sophra.unistone.Entity.Project;
import com.sophra.unistone.Entity.ProjectUser;
import com.sophra.unistone.Entity.TaskBoard;
import com.sophra.unistone.Entity.TaskBoardStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskBoardRepository extends JpaRepository<TaskBoard, Long> {

    // 작업보드 분류로 모든 보드찾기
    List<TaskBoard> findByTaskBoardStatusId(Long taskBoardStatusID);
}
