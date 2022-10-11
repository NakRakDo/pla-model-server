package com.example.pmb.global.config.security.jwt;

import com.example.pmb.domain.auth.component.UserService;
import com.example.pmb.domain.auth.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtJsonLoginAuthManager implements AuthenticationManager, MessageSourceAware {

    private final JwtJsonLoginAuthProvider authProvider;
    private final UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication)
        throws AuthenticationException {

        Authentication token = authProvider.authenticate(authentication);

        return token;
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {

    }
}
