package com.sophra.unistone.Service;

import com.sophra.unistone.Entity.Chat;
import com.sophra.unistone.Repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    // 채팅방 id로 채팅들 가져오기
    public List<Chat> getChatsByChatRoomId(Long chatRoomId) {
        return chatRepository.findByChatRoomId(chatRoomId);
    }
}
