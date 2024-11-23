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

}
