package com.sophra.unistone.Controller;

import com.sophra.unistone.DTO.ScrapDTO;
import com.sophra.unistone.Entity.*;
import com.sophra.unistone.Repository.ChatRepository;
import com.sophra.unistone.Repository.ProjectRepository;
import com.sophra.unistone.Repository.ScrapChatRepository;
import com.sophra.unistone.Repository.ScrapRepository;
import com.sophra.unistone.UserCheck;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class ScrapController {
    
    // 유저 확인용 클래스
    UserCheck userCheck;

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private ScrapChatRepository scrapChatRepository;
    @Autowired
    private ScrapRepository scrapRepository;

    //스크랩 흐름
    // 스크랩할 채팅 모두 선택 -> 스크랩 버튼 누름
    // -> 스크랩 이름을 작성하세요 알림 -> 스크랩 생성


    // 스크랩 생성 요청
    @PostMapping("/api/scrap/create")
    public ResponseEntity<?> CreateScrap(@RequestBody ScrapDTO scrap, HttpSession session) {

        // 유저 확인
        Users user = userCheck.validateLoggedInUser(session);

        // 프로젝트 가져오기
        Project project = projectRepository.findById(scrap.getProjectId())
                .orElseThrow(() -> new IllegalArgumentException("프로젝트를 찾을 수 없습니다"));

        // 스크랩 생성
        Scrap newScrap = new Scrap();
        newScrap.setScrapName(scrap.getScrapName());
        newScrap.setUser(user);
        newScrap.setProject(project);


        // 스크랩 채팅 생성
        for(int i = 0; i <= scrap.getChatIds().size(); i++)
        {
            Long chatId = scrap.getChatIds().get(i); // i번째 채팅 ID
            Optional<Chat> chat = chatRepository.findById(chatId); // 채팅 찾기
            
            // 새로운 스크랩 채팅 생성
            ScrapChat newScrapChat = new ScrapChat();
            newScrapChat.setScrap(newScrap);
            newScrapChat.setChat(chat.get());

            // 스크랩 채팅 저장
            scrapChatRepository.save(newScrapChat);
        }

        return ResponseEntity.ok("스크랩 성공");

    }
    
    // 스크랩 목록 가져오기
    @GetMapping("api/scrap/{projectId}")
    public ResponseEntity<?> GetScrapList(@PathVariable("projectId") String projectId, HttpSession session) {

        // 유저 확인
        Users user = userCheck.validateLoggedInUser(session);

        // 프로젝트 가져오기
        Project project = projectRepository.findById(Long.valueOf(projectId))
                .orElseThrow(() -> new IllegalArgumentException("프로젝트를 찾을 수 없습니다"));


        // 스크랩 목록 가져오기
        List<Scrap> scrapList = scrapRepository.findByUserAndProject(user, project);

        // 응답 데이터 생성
        List<Map<String, Object>> response = scrapList.stream().map(scrap -> {
            Map<String, Object> scrapData = new HashMap<>();
            scrapData.put("id", scrap.getId());
            scrapData.put("scrapName", scrap.getScrapName());
            scrapData.put("userName", scrap.getUser().getUserName());
            return scrapData;
        }).toList();


        return ResponseEntity.ok(response);
        
    }

    // 스크랩 내용 가져오기
    @GetMapping("api/scrap/{scrapId}")
    public ResponseEntity<?> GetScrapContent(@PathVariable("scrapId") String scrapId, HttpSession session) throws IllegalAccessException {

        // 유저 확인
        Users user = userCheck.validateLoggedInUser(session);

        // 스크랩 가져오기
        Scrap scrap = scrapRepository.findById(Long.valueOf(scrapId))
                .orElseThrow(() -> new IllegalArgumentException("스크랩을 찾을 수 없습니다"));


        // 권한 확인
        if (!scrap.getUser().getId().equals(user.getId())) {
            throw new IllegalAccessException("이 스크랩에 접근할 권한이 없습니다.");
        }

        // ScrapChat에서 스크랩에 연결된 채팅 가져오기
        List<ScrapChat> scrapChats = scrapChatRepository.findByScrap((scrap));

        // 응답 데이터 생성
        Map<String, Object> response = new HashMap<>();
        response.put("id", scrap.getId());
        response.put("scrapName", scrap.getScrapName());
        response.put("userName", scrap.getUser().getUserName());
        response.put("chats", scrapChats.stream().map(scrapChat -> {
            Chat chat = scrapChat.getChat();
            Map<String, Object> chatData = new HashMap<>();
            chatData.put("id", chat.getId());
            chatData.put("content", chat.getContent());
            chatData.put("userName", chat.getUser().getUserName());
            return chatData;
        }).toList());

        return ResponseEntity.ok(response);

    }







}
