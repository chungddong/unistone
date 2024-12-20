package com.sophra.unistone.Service;

import com.sophra.unistone.Entity.ChatRoomNoti;
import com.sophra.unistone.Repository.ChatRoomNotiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatRoomNotiService {

    @Autowired
    private ChatRoomNotiRepository chatRoomNotiRepository;


    // 특정 채팅방의 공지 목록 가져오기
    public List<ChatRoomNoti> getNoticesByChatRoomId(Long chatRoomId) {
        return chatRoomNotiRepository.findByChatRoomId(chatRoomId);
    }

}
