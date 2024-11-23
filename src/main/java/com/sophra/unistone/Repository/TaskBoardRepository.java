package com.sophra.unistone.Repository;

import com.sophra.unistone.Entity.TaskBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskBoardRepository extends JpaRepository<TaskBoard, Long> {
}
