package com.sophra.unistone.Service;

import com.sophra.unistone.Entity.ChatRoom;
import com.sophra.unistone.Entity.ChatUser;
import com.sophra.unistone.Repository.ChatUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatUserService {

    @Autowired
    private ChatUserRepository chatUserRepository;

    // 채팅방 ID로 해당 채팅방의 참가자 목록 가져오기
    public List<ChatUser> getChatUsersByChatRoomId(Long chatRoomId) {
        return chatUserRepository.findByChatRoomId(chatRoomId);
    }


}
