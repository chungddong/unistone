package com.sophra.unistone.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "Chat")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Chat {

    @Id
    @GeneratedValue
    private Long id;

    private String content;

    private LocalDateTime sentAt;

    @ManyToOne
    @JoinColumn(name = "chatroom_id", nullable = false)
    private ChatRoom chatRoom; // 채팅방과 연결

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user; // 사용자와 연결

}
