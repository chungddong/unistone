package com.sophra.unistone.Service;

import com.sophra.unistone.Entity.ProjectUser;
import com.sophra.unistone.Entity.Users;
import org.springframework.beans.factory.annotation.Autowired;

import com.sophra.unistone.Entity.Project;
import com.sophra.unistone.Repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;


    // 프로젝트 등록하는 메서드
    public Project registerProject(Project project) {   
        return projectRepository.save(project);
    }

    /**
     * 프로젝트 정보를 조회
     * @param projectId 조회할 프로젝트 ID
     * @return 유저가 참여한 프로젝트 리스트
     */
    public Optional<Project> findProjectById(Long projectId) {
        return projectRepository.findById(projectId);
    }
    
}
