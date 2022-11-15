package com.example.pmb.domain.auth.repository;


import com.example.pmb.domain.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoryImpl extends JpaRepository<User, Long> {

    User findUserByUsername(String username);
}
