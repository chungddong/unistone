package com.sophra.unistone.Repository;

import com.sophra.unistone.Entity.ProjectUser;
import com.sophra.unistone.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectUserRepository extends JpaRepository<ProjectUser, Long>{
    List<ProjectUser> findByUser(Users user);
}