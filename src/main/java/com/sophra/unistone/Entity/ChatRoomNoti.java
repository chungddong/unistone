package com.sophra.unistone.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "ChatRoomNoti")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomNoti { //채팅방 공지

    @Id
    @GeneratedValue
    private Long id; //공지 번호

    private String notiTitle; // 공지 제목

    private String notiContent; //공지 내용

    @ManyToOne
    @JoinColumn(name = "chatroom_id", nullable = false)
    private ChatRoom chatRoom; // 채팅방과 연결


}
