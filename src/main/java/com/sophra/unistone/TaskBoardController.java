package com.sophra.unistone;

import com.sophra.unistone.DTO.TaskBoardDTO;
import com.sophra.unistone.Entity.Project;
import com.sophra.unistone.Entity.TaskBoard;
import com.sophra.unistone.Entity.TaskBoardStatus;
import com.sophra.unistone.Entity.Users;
import com.sophra.unistone.Repository.TaskBoardRepository;
import com.sophra.unistone.Repository.TaskBoardStatusRepository;
import com.sophra.unistone.Service.ProjectService;
import com.sophra.unistone.Service.UsersService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class TaskBoardController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private TaskBoardRepository taskBoardRepository;


    @Autowired
    private TaskBoardStatusRepository taskBoardStatusRepository;

    // 유저 확인용 클래스
    UserCheck userCheck;



    // 작업보드 분류 생성 요청
    @PostMapping("/api/taskboard/status")
    public ResponseEntity<?> InfoProject(@RequestBody Map<String, String> requestBody, HttpSession session) {

        // 유저 확인
        Users user = userCheck.validateLoggedInUser(session);
        String statusName = requestBody.get("statusName");

        // 요청된 프로젝트 ID 확인
        Long projectId = Long.valueOf(requestBody.get("projectId"));
        if (projectId == null) {
            return ResponseEntity.badRequest().body("유효한 프로젝트 ID가 필요합니다.");
        }

        // 프로젝트 ID로 특정 프로젝트 조회
        Optional<Project> selectProject = projectService.findProjectById(projectId);

        // 프로젝트 존재 여부 확인
        if (selectProject.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("프로젝트를 찾을 수 없습니다.");
        }

        // 작업보드상태 추가
        TaskBoardStatus status = new TaskBoardStatus();
        status.setProject(selectProject.get());
        status.setStatusName(statusName);

        taskBoardStatusRepository.save(status);

        return ResponseEntity.ok(selectProject.get());
    }

    // TODO : 프로젝트 리스트 요청
    @GetMapping("/api/taskboard/status")
    public ResponseEntity<?> ListProject(HttpSession session) {

        Users loginuser = (Users) session.getAttribute("user");
        Optional<Users> user = usersService.findbyEmail(loginuser.getEmail());

        // 유저 확인
        if(user.isEmpty()) { return ResponseEntity.badRequest().body("로그인이 필요합니다."); }

        // TODO : 작업보드 분류 리스트 반환

        return ResponseEntity.ok("ㄴㅇㄹ");
    }



    // 새로운 작업보드 추가 요청
    @PostMapping("/api/taskboard/create")
    public ResponseEntity<?> InfoProject(@RequestBody TaskBoardDTO taskBoardDTO, HttpSession session) {

        // 유저 확인
        Users user = userCheck.validateLoggedInUser(session);

        // 프로젝트 ID로 특정 프로젝트 조회
        Optional<Project> selectProject = projectService.findProjectById(taskBoardDTO.getProjectId());

        // 프로젝트 존재 여부 확인
        if (selectProject.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("프로젝트를 찾을 수 없습니다.");
        }

        // 요청 날린 TaskBoard
        TaskBoard selectTaskBoard = taskBoardDTO.getTaskBoard();

        // 작업보드상태 추가
        TaskBoard newTaskBoard = new TaskBoard();
        newTaskBoard.setTaskName(selectTaskBoard.getTaskName());
        newTaskBoard.setTaskContent(selectTaskBoard.getTaskContent());
        newTaskBoard.setStartDate(selectTaskBoard.getStartDate());
        newTaskBoard.setEndDate(selectTaskBoard.getEndDate());

        // 새로운 작업보드 추가
        taskBoardRepository.save(newTaskBoard);
        
        // TODO : 작업보드 사용자 추가 코드 필요함

        return ResponseEntity.ok("작업 보드 추가 성공");
    }


    


}
