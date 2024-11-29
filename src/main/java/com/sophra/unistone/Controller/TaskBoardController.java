package com.sophra.unistone.Controller;

import com.sophra.unistone.DTO.TaskBoardDTO;
import com.sophra.unistone.Entity.*;
import com.sophra.unistone.Repository.TaskBoardRepository;
import com.sophra.unistone.Repository.TaskBoardStatusRepository;
import com.sophra.unistone.Repository.TaskBoardUserRepository;
import com.sophra.unistone.Repository.UsersRepository;
import com.sophra.unistone.Service.*;
import com.sophra.unistone.UserCheck;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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

    @Autowired
    private TaskBoardService taskBoardService;

    // 유저 확인용 클래스
    UserCheck userCheck;
    @Autowired
    private TaskBoardStatusService taskBoardStatusService;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private TaskBoardUserRepository taskBoardUserRepository;
    @Autowired
    private TaskBoardUserService taskBoardUserService;


    // 작업보드 분류 생성 요청
    @PostMapping("/api/taskboard/status")
    public ResponseEntity<?> CreateTaskBoardStatus(@RequestBody Map<String, String> requestBody, HttpSession session) {

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

    // 작업보드 분류 리스트 요청
    @PostMapping("/api/taskboard/status/list")
    public ResponseEntity<?> ListTaskBoardStatus(@RequestBody Map<String, Long> requestBody, HttpSession session) {

        // 유저 확인
        Users user = userCheck.validateLoggedInUser(session);

        // 요청된 프로젝트 ID 확인
        Long projectId = requestBody.get("projectId");
        if (projectId == null) {
            return ResponseEntity.badRequest().body("유효한 프로젝트 ID가 필요합니다.");
        }

        List<TaskBoardStatus> taskBoardStatus = taskBoardStatusService.getTaskBoardStatusByProjectId(projectId);

        return ResponseEntity.ok(taskBoardStatus);
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

        // 새로운 작업보드 추가
        taskBoardRepository.save(selectTaskBoard);
        
        // 작업보드 사용자 추가 코드 필요함
        List<Long> userIds = taskBoardDTO.getUserIds(); //유저 아이디 목록

        for(int i =0; i < userIds.size(); i++) {
            Optional<Users> users = usersRepository.findById(userIds.get(i));

            // 작업보드 유저 추가
            TaskBoardUser taskBoardUser = new TaskBoardUser();
            taskBoardUser.setProject(selectProject.get());
            taskBoardUser.setUser(users.get());

            // 작업보드 유저 저장
            taskBoardUserRepository.save(taskBoardUser);

        }

        

        return ResponseEntity.ok("작업 보드 추가 성공");
    }


    // 작업보드 리스트 가져오기
    @GetMapping("/api/taskboard/{taskBoardStatusId}")
    public ResponseEntity<?> ListTaskBoard(@PathVariable("taskBoardStatusId") String taskBoardStatusId, HttpSession session) {
        // 유저 확인
        Users user = userCheck.validateLoggedInUser(session);

        //작업보드 분류 ID 가져오기
        Long taskBoardStatusID = Long.valueOf(taskBoardStatusId);

        // 작업보드 분류 id 로 모든 작업보드 가져오기
        List<TaskBoard> selectStatusBoards = taskBoardService.getTaskBoardsByStatusId(taskBoardStatusID);

        // 작업보드와 관련된 유저 목록도 같이 반환
        List<Map<String, Object>> response = selectStatusBoards.stream().map(taskBoard -> {
            Map<String, Object> taskBoardData = new HashMap<>();
            taskBoardData.put("id", taskBoard.getId());
            taskBoardData.put("taskName", taskBoard.getTaskName());
            taskBoardData.put("taskContent", taskBoard.getTaskContent());
            taskBoardData.put("startDate", taskBoard.getStartDate());
            taskBoardData.put("endDate", taskBoard.getEndDate());

            // 관련된 사용자 가져오기
            List<TaskBoardUser> taskBoardUsers = taskBoardUserService.getUsersByProjectId(taskBoard.getTaskBoardStatus().getId());
            List<Map<String, Object>> usersData = taskBoardUsers.stream().map(taskBoardUser -> {
                Map<String, Object> userData = new HashMap<>();
                userData.put("userId", taskBoardUser.getUser().getId());
                userData.put("userName", taskBoardUser.getUser().getUserName());
                return userData;
            }).toList();

            taskBoardData.put("users", usersData);
            return taskBoardData;
        }).toList();

        return ResponseEntity.ok(response);

    }



    


}
