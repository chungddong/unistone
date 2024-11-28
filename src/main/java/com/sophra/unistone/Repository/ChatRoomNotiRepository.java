package com.sophra.unistone.Repository;

import com.sophra.unistone.Entity.ChatRoomNoti;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRoomNotiRepository extends JpaRepository<ChatRoomNoti, Long> {

    // 채팅방 id 로 특정 채팅방의 공지사항 리스트 가져오기
    List<ChatRoomNoti> findByChatRoomId(Long chatRoomId);

}
