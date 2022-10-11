package com.example.pmb.global.config.security.jwt;

import com.example.pmb.domain.auth.component.UserService;
import com.example.pmb.domain.auth.entity.User;
import com.example.pmb.global.config.security.PasswordEncoderConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtJsonLoginAuthProvider implements AuthenticationProvider {

    private final UserService userService;
    private final PasswordEncoderConfig passwordEncoderConfig;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String)authentication.getCredentials();

        User user = (User)userService.loadUserByUsername(username);
        //throw new UsernameNotFoundException("test");

        // password 일치하지 않으면!
        /*if(!passwordEncoder.matches(password,accountContext.getAccount().getPassword())){
            throw new BadCredentialsException("BadCredentialsException");
        }*/

        UsernamePasswordAuthenticationToken token
            = new UsernamePasswordAuthenticationToken(
            user.getUsername(),
            user.getPassword(),
            user.getAuthorities());

        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
