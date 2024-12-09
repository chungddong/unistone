package com.sophra.unistone.Controller;

import com.sophra.unistone.Entity.*;
import com.sophra.unistone.Repository.ChatRoomRepository;
import com.sophra.unistone.Repository.ChatUserRepository;
import com.sophra.unistone.Repository.ProjectRepository;
import com.sophra.unistone.Repository.ProjectUserRepository;
import com.sophra.unistone.Service.ChatRoomService;
import com.sophra.unistone.Service.ProjectService;
import com.sophra.unistone.Service.ProjectUserService;
import com.sophra.unistone.Service.UsersService;
import com.sophra.unistone.UserCheck;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectUserRepository projectUserRepository;

    @Autowired
    private UsersService usersService;

    @Autowired
    private ProjectUserService projectUserService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Autowired
    private ChatUserRepository chatUserRepository;


    // 유저 확인용 클래스
    UserCheck userCheck;
    @Autowired
    private ChatRoomService chatRoomService;

    // 프로젝트 생성 요청 처리
    @PostMapping("/api/project/create")
    public ResponseEntity<?> CreateProject(@RequestBody Project pjQuery, HttpSession session) {

        // 유저 확인
        Users usera = (Users)session.getAttribute("user");
        Optional<Users> user = usersService.findbyEmail(usera.getEmail());


        // 새로운 프로젝트 생성
        Project newProject = new Project();
        newProject.setProjectName(pjQuery.getProjectName()); //프로젝트 이름
        newProject.setStartDate(pjQuery.getStartDate()); //프로젝트 시작일
        newProject.setEndDate(pjQuery.getEndDate()); //프로젝트 종료일
        newProject.setDescription(pjQuery.getDescription());   //프로젝트 설명

        // 새로운 프로젝트 DB에 저장
        projectRepository.save(newProject);

        // 프로젝트 참가자 유저 저장 - 생성한 사람은 관리자
        ProjectUser projectUser = new ProjectUser();
        projectUser.setUser(user.get()); // 참가자 유저 정보 연결
        projectUser.setProject(newProject); // 참가자 프로젝트 정보 연결
        projectUser.setUserRole("Manager"); // 관리자 역할 부여

        // 새로운 프로젝트 유저 저장 - 관리자
        projectUserRepository.save(projectUser);

        // 기본 채팅방(전체) 생성
        ChatRoom defaultChatRoom = new ChatRoom();
        defaultChatRoom.setProject(newProject);
        defaultChatRoom.setName("전체 채팅방");
        chatRoomRepository.save(defaultChatRoom);

        // 기본 채팅방 참여자 생성
        ChatUser defaultChatUser = new ChatUser();
        defaultChatUser.setUser(user.get());
        defaultChatUser.setChatRoom(defaultChatRoom);
        chatUserRepository.save(defaultChatUser);

        return ResponseEntity.ok("Confirm");
    }


    // 프로젝트 리스트 요청
    @GetMapping("/api/project/list")
    public ResponseEntity<?> ListProject(HttpSession session) {

        // 유저 확인
        Users usera = (Users)session.getAttribute("user");
        Optional<Users> user = usersService.findbyEmail(usera.getEmail());

        // 유저의 모든 프로젝트 리스트 반환
        List<Project> userProjects = projectUserService.findAllProjectsByUser(user.get());


        return ResponseEntity.ok(userProjects);
    }

    // 프로젝트 정보(속성) 요청
    @PostMapping("/api/project/info")
    public ResponseEntity<?> InfoProject(@RequestBody Map<String, Long> requestBody, HttpSession session) {

        // 유저 확인
        Users usera = (Users)session.getAttribute("user");
        Optional<Users> user = usersService.findbyEmail(usera.getEmail());

        // 요청된 프로젝트 ID 확인
        Long projectId = requestBody.get("projectId");
        if (projectId == null) {
            return ResponseEntity.badRequest().body("유효한 프로젝트 ID가 필요합니다.");
        }


        // 프로젝트 ID로 특정 프로젝트 조회
        Optional<Project> selectProject = projectService.findProjectById(projectId);

        // 프로젝트 존재 여부 확인
        if (selectProject.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("프로젝트를 찾을 수 없습니다.");
        }
        
        // TODO : 유저가 참가자로 속해있는 프로젝트인지도 확인해야함 -보안적 부분

        return ResponseEntity.ok(selectProject.get());
    }


    // 프로젝트 참가자 초대
    @PostMapping("/api/project/invite")
    public ResponseEntity<?> InviteProject(@RequestBody Map<String, String> requestBody, HttpSession session) {

        // 유저 확인
        Users user = userCheck.validateLoggedInUser(session);

        // 초대할 유저 이메일과 프로젝트 ID 요청으로 받아와야함
        String inviteUser = requestBody.get("inviteEmail");
        Long projectId = Long.valueOf(requestBody.get("projectId"));

        // 예외처리
        Optional<Users> invitee = usersService.findbyEmail(inviteUser);
        if (invitee.isEmpty()) {
            return ResponseEntity.badRequest().body("초대하려는 유저를 찾을 수 없습니다.");
        }

        Optional<Project> project = projectService.findProjectById(projectId);
        if (project.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("프로젝트를 찾을 수 없습니다.");
        }

        // TODO :  프로젝트 권한 확인 (예: 관리자 권한)
        

        ProjectUser newProjectUser = new ProjectUser();
        newProjectUser.setUser(invitee.get());
        newProjectUser.setProject(project.get());
        newProjectUser.setUserRole("member"); // 기본 역할로 설정
        projectUserRepository.save(newProjectUser);
        
        //프로젝트의 기본 채팅방 가져오기
        Optional<ChatRoom> defaultChatRoom = chatRoomService.findChatRoomById(project.get().getId());
        if (defaultChatRoom.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("기본 채팅방을 찾을 수 없습니다.");
        }

        //프로젝트의 기본 채팅방에 유저 추가
        ChatUser inviteChatUser = new ChatUser();
        inviteChatUser.setUser(invitee.get());
        inviteChatUser.setChatRoom(defaultChatRoom.get());
        chatUserRepository.save(inviteChatUser);


        return ResponseEntity.ok("초대 성공");
    }


    // 프로젝트 참가자 목록 가져오기
    @PostMapping("/api/project/users")
    public ResponseEntity<?> ListProjectUser(@RequestBody Map<String, Long> requestBody, HttpSession session) {


        // 요청된 프로젝트 ID 확인
        Long projectId = requestBody.get("projectId");
        if (projectId == null) {
            return ResponseEntity.badRequest().body("유효한 프로젝트 ID가 필요합니다.");
        }


        // 프로젝트 ID로 특정 프로젝트 조회
        Optional<Project> selectProject = projectService.findProjectById(projectId);

        // 프로젝트 존재 여부 확인
        if (selectProject.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("프로젝트를 찾을 수 없습니다.");
        }

        // 모든 참가자 리스트 가져오기
        List<String> allProjectUserNames = projectUserService.findAllUserNameByProject(selectProject.get());

        System.out.println(allProjectUserNames);

        return ResponseEntity.ok(allProjectUserNames);
    }

}
