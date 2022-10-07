package com.example.pmb.global.config.security.jwt;

import com.example.pmb.domain.auth.component.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtJsonLoginAuthManager implements AuthenticationManager {

    private JwtJsonLoginAuthProvider authProvider;

    @Override
    public Authentication authenticate(Authentication authentication)
        throws AuthenticationException {
        return null;
    }
}
