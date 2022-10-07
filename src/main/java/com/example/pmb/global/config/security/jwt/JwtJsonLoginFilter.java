package com.example.pmb.global.config.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

@Component
public class JwtJsonLoginFilter extends AbstractAuthenticationProcessingFilter {
    //https://ttl-blog.tistory.com/104

    private final ObjectMapper objectMapper = new ObjectMapper();
    public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "username";
    public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "password";
    private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER =
        new AntPathRequestMatcher("/login","POST");
    private String usernameParameter = SPRING_SECURITY_FORM_USERNAME_KEY;

    private String passwordParameter = SPRING_SECURITY_FORM_PASSWORD_KEY;

    private boolean postOnly = true;

    @Autowired
    public JwtJsonLoginFilter(JwtJsonLoginAuthManager authManager, JwtJsonLoginAuthSuccessHandler successHandler, JwtJsonLoginAuthFailureHandler failureHandler) {
        super(DEFAULT_ANT_PATH_REQUEST_MATCHER, authManager);
        setAuthenticationSuccessHandler(successHandler);
        setAuthenticationFailureHandler(failureHandler);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
        HttpServletResponse response)
        throws AuthenticationException, IOException, ServletException {
        return null;
    }
}
