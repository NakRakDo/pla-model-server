package com.example.pmb.domain.web.auth;


import com.example.pmb.domain.auth.component.UserService;
import com.example.pmb.domain.auth.entity.User;
import com.example.pmb.domain.test.TestUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/login")
    public String doLogin(@RequestBody User user){
        /*
        * curl -d "{""email"":""kyunghun96@gmail.com""}" -H "Content-Type: application/json" -X POST localhost:8080/auth/login
        * */
        String test = "";
        userService.doLogin(user);
        System.out.println(user.getName());
        return null;
    }

    @GetMapping(path = "/all")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping(path = "/one")
    public User getUser(@RequestParam String email){
        return userService.getUser(email);
    }
}
