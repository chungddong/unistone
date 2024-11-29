package com.sophra.unistone.Repository;

import com.sophra.unistone.Entity.Project;
import com.sophra.unistone.Entity.Scrap;
import com.sophra.unistone.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScrapRepository extends JpaRepository<Scrap, Long> {

    // 유저와 프로젝트로 스크랩 찾기
    List<Scrap> findByUserAndProject(Users user, Project project);
    
}
