package com.example.pmb.global.config.security.jwt;

import com.example.pmb.domain.auth.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
        new AntPathRequestMatcher("/auth/login","POST");
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
    public Authentication attemptAuthentication(HttpServletRequest request,HttpServletResponse response)
        throws AuthenticationException, IOException, ServletException {
        if (this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Only [POST] method supported: Current method ::> " + request.getMethod());
        }

        /*
        * curl -X POST -H "Content-Type: application/json" -d "{\"username\": \"kkh\", \"password\": \"kkk\"}" localhost:8080/auth/login
        * */
        UserDto userDto = objectMapper.readValue(request.getReader(), UserDto.class);

        String username = obtainUsername(userDto);
        username = (username != null) ? username.trim() : "";
        String password = obtainPassword(userDto);
        password = (password != null) ? password : "";
        UsernamePasswordAuthenticationToken authRequest = UsernamePasswordAuthenticationToken.unauthenticated(username,
            password);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    protected String obtainUsername(UserDto userDto) throws IOException {
        return userDto.getUsername();
        //return request.getParameter(this.usernameParameter);
    }

    protected String obtainPassword(UserDto userDto) throws IOException {
        return userDto.getPassword();
    }
}
