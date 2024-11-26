package com.sophra.unistone.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoom {

    //채팅방 엔티티

    @Id
    @GeneratedValue
    private Long id;

    private String name; //채팅방 제목

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)  //채팅방 소속 프로젝트
    private Project project; // 프로젝트와 연결 

}
