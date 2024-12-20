package com.sophra.unistone.Repository;

import com.sophra.unistone.Entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    List<Chat> findByChatRoomId(Long chatRoomId);
}
