package com.example.pmb.domain.web.test;

import com.example.pmb.domain.test.TestUserEntity;
import com.example.pmb.domain.test.TestUserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/test")
@RequiredArgsConstructor
public class TestController {


    private final TestUserService testUserService;

    @GetMapping(path = "/all")
    public List<TestUserEntity> getAllUsers(){
        return testUserService.getAllUsers();
    }

    @GetMapping(path = "/one")
    public TestUserEntity getUser(@RequestParam String email){
        return testUserService.getUser(email);
    }
}
