package com.sophra.unistone.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.sophra.unistone.Entity.Project;
import com.sophra.unistone.Repository.ProjectRepository;

public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;


    // 프로젝트 등록하는 메서드
    public Project registerProject(Project project) {   
        return projectRepository.save(project);
    }
    
}
