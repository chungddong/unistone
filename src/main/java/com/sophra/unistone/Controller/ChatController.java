package com.sophra.unistone.Controller;

import com.sophra.unistone.Entity.Chat;
import com.sophra.unistone.Entity.ChatRoom;
import com.sophra.unistone.Entity.Project;
import com.sophra.unistone.Repository.ChatRepository;
import com.sophra.unistone.Repository.ChatRoomRepository;
import com.sophra.unistone.Repository.ProjectRepository;
import com.sophra.unistone.Service.ChatService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ChatController {

    @Autowired
    private ChatRepository chatRepository; // 채팅 서비스

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    //메시지 전송 요청
    @MessageMapping("/send")
    @SendTo("/topic/chatroom/{chatRoomId}")
    public Chat sendMessage(Chat message) {

        // 메시지 저장
        Chat savedMessage = chatRepository.save(message);

        return savedMessage;
    }

    //메시지 조회 요청
    /*@GetMapping("/{chatRoomId}")
    public ResponseEntity<List<Chat>> getChats(
            @PathVariable String chatRoomId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        List<Chat> chats // = chatService.getChatsByChatRoomId(chatRoomId, page, size);
        return ResponseEntity.ok(chats);
    }*/


    //채팅방 생성 요청
    @PostMapping("/api/chatroom/create")
    public ResponseEntity<?> CreateChatRoom(@RequestBody ChatRoom chatRoom, HttpSession session) {
        // 프로젝트 확인
        Optional<Project> projectOptional = projectRepository.findById(chatRoom.getProject().getId());
        if (projectOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("해당 프로젝트를 찾을 수 없습니다.");
        }

        // 새 채팅방 생성
        ChatRoom newChatRoom = new ChatRoom();
        newChatRoom.setName(chatRoom.getName()); // 채팅방 이름 설정
        newChatRoom.setProject(projectOptional.get());  // 프로젝트 연결

        // 채팅방 저장
        chatRoomRepository.save(newChatRoom);

        return ResponseEntity.ok("채팅방 생성 성공");
    }

   /* //채팅방 리스트 가져오기
    @("/api/chatroom/list")
    public ResponseEntity<?> GetChatRoomList(HttpSession session) {



    }*/
    


}
