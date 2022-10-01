package com.example.pmb.domain.test;


import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestUserServiceImpl implements TestUserService{

    private final TestUserRepository testUserRepository;

    @Override
    public TestUserEntity getUser(String email) {
        return testUserRepository.findTestUserByEmail(email);
    }
    @Override
    public List<TestUserEntity> getAllUsers() {
        return testUserRepository.findAll();
    }

}
