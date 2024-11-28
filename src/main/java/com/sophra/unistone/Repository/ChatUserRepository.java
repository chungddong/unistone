package com.sophra.unistone.Repository;

import com.sophra.unistone.Entity.ChatUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatUserRepository extends JpaRepository<ChatUser, Long> {
    List<ChatUser> findByChatRoomId(Long chatRoomId);
}
