package com.example.pmb.domain.auth.component;


import com.example.pmb.domain.auth.dto.UserDto;
import com.example.pmb.domain.auth.entity.User;
import com.example.pmb.domain.auth.repository.UserRepository;
import com.example.pmb.domain.auth.repository.UserRepositoryImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @Override
    public boolean doLogin(UserDto user) {
        User finduser = userRepository.findUserByUsername(user.getUsername());
        if(finduser.getUsername() == null) return false;
        return true;
    }

    @Override
    public User getUser(String email){
        return userRepository.findUserByUsername(email);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username);
        //return null;
    }

    @Override
    public String loadPasswordByUsername(String name) throws BadCredentialsException {
        return null;
    }
}
