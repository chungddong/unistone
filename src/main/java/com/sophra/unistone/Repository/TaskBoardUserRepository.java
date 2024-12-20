package com.sophra.unistone.Repository;

import com.sophra.unistone.Entity.TaskBoardUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskBoardUserRepository extends JpaRepository<TaskBoardUser, Long> {
    // 특정 프로젝트에 연결된 TaskBoardUser 가져오기
    List<TaskBoardUser> findByProjectId(Long projectId);
}
