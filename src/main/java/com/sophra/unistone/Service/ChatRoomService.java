package com.sophra.unistone.Service;

import com.sophra.unistone.Entity.ChatRoom;
import com.sophra.unistone.Repository.ChatRoomRepository;
import com.sophra.unistone.Repository.ProjectUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatRoomService {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    // 프로젝트 id로 채팅방 리스트 가져오기
    public List<ChatRoom> getChatRoomsByProjectId(Long projectId) {
        return chatRoomRepository.findByProjectId(projectId);
    }

}
