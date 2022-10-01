package com.example.pmb.domain.auth.component;

import com.example.pmb.domain.auth.entity.User;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;



public interface UserService extends UserDetailsService {
    public User getUser(String email);

    public List<User> getAllUsers();
}
