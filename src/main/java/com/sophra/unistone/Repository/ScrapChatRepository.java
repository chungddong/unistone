package com.sophra.unistone.Repository;

import com.sophra.unistone.Entity.Scrap;
import com.sophra.unistone.Entity.ScrapChat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScrapChatRepository extends JpaRepository<ScrapChat, Long> {
    List<ScrapChat> findByScrap(Scrap scrap);
}
