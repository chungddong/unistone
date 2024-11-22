package com.sophra.unistone;

import com.sophra.unistone.Entity.Project;
import com.sophra.unistone.Entity.Users;
import com.sophra.unistone.Repository.ProjectRepository;
import com.sophra.unistone.Service.UsersService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UsersService usersService;

    // 프로젝트 생성 요청 처리
    @PostMapping("/api/project/create")
    public ResponseEntity<?> CreateProject(@RequestBody Project pjQuery, HttpSession session) {

        Users loginuser = (Users) session.getAttribute("user");
        Optional<Users> user = usersService.findbyEmail(loginuser.getEmail());

        // 유저 확인
        if(user.isEmpty()) { return ResponseEntity.ofNullable("로그인이 필요합니다."); }

        // 새로운 프로젝트 생성
        Project newProject = new Project();
        newProject.setProjectName(pjQuery.getProjectName()); //프로젝트 이름
        newProject.setStartDate(pjQuery.getStartDate()); //프로젝트 시작일
        newProject.setEndDate(pjQuery.getEndDate()); //프로젝트 종료일
        newProject.setDescription(pjQuery.getDescription());   //프로젝트 설명

        // 새로운 프로젝트 DB에 저장
        projectRepository.save(newProject);

        // 프로젝트 참가자 유저 저장


        return ResponseEntity.ok("프로젝트 생성 성공");
    }

}
