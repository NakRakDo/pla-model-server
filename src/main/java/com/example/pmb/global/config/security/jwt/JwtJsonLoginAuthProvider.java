package com.example.pmb.global.config.security.jwt;

import com.example.pmb.domain.auth.component.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtJsonLoginAuthProvider implements AuthenticationProvider {

    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication)
        throws AuthenticationException {
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
