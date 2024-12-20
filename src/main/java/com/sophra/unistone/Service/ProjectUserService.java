package com.sophra.unistone.Service;

import com.sophra.unistone.Entity.Project;
import com.sophra.unistone.Entity.ProjectUser;
import com.sophra.unistone.Entity.Users;
import com.sophra.unistone.Repository.ProjectUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectUserService {

    @Autowired
    private ProjectUserRepository projectUserRepository;


    /**
     * 유저가 참여한 모든 프로젝트를 반환
     * @param user 유저 정보
     * @return 유저가 참여한 프로젝트 리스트
     */
    public List<Project> findAllProjectsByUser(Users user) {
        return projectUserRepository.findByUser(user).stream()
                .map(ProjectUser::getProject) // ProjectUser에서 Project를 추출
                .collect(Collectors.toList());
    }


    /**
     * 프로젝트로 모든 유저 이름을 반환
     * @param project 프로젝트 ID
     * @return 프로젝트에 참여한 유저 이름 리스트
     */
    public List<String> findAllUserNameByProject(Project project) {
        return projectUserRepository.findByProject(project).stream()
                .map(projectUser -> projectUser.getUser().getUserName()) // ProjectUser에서 유저 이름 추출
                .collect(Collectors.toList());
    }

}
