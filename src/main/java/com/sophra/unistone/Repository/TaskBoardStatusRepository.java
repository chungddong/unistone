package com.sophra.unistone.Repository;

import com.sophra.unistone.Entity.ProjectUser;
import com.sophra.unistone.Entity.TaskBoardStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskBoardStatusRepository extends JpaRepository<TaskBoardStatus, Long>{
}
