package com.example.pmb.domain.test;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface TestUserRepository extends JpaRepository<TestUserEntity, Long> {
    TestUserEntity findTestUserByEmail(String email);
}
