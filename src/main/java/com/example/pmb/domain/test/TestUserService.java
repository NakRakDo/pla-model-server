package com.example.pmb.domain.test;

import java.util.List;

public interface TestUserService {
    public TestUserEntity getUser(String email);

    public List<TestUserEntity> getAllUsers();
}
