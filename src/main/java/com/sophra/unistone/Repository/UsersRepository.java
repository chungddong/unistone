package com.sophra.unistone.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sophra.unistone.Entity.Users;

public interface UsersRepository extends JpaRepository<Users, Long>{
    Optional<Users> findByEmail(String email);

}
