package com.example.pmb.domain.auth.component;


import com.example.pmb.domain.auth.entity.User;
import com.example.pmb.domain.auth.repository.UserRepository;
import java.util.List;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @GetMapping(path = "/all")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping(path = "/one")
    public User getUser(@RequestParam String email){
        return userRepository.findUserByEmail(email);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}