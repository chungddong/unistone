package com.sophra.unistone.Repository;

import com.sophra.unistone.Entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    List<ChatRoom> findByProjectId(Long projectId);
}
