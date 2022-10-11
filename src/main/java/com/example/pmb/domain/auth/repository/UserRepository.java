package com.example.pmb.domain.auth.repository;


import com.example.pmb.domain.auth.entity.User;
import com.example.pmb.domain.test.TestUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmail(String email);
    User findUserByUsername(String username);
}
