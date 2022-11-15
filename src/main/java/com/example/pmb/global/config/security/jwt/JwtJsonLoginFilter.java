package com.example.pmb.global.config.security.jwt;

import com.example.pmb.domain.auth.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.log.LogMessage;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
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
    private final String usernameParameter = SPRING_SECURITY_FORM_USERNAME_KEY;

    private final String passwordParameter = SPRING_SECURITY_FORM_PASSWORD_KEY;

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

        setDetails(request,authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
        Authentication authResult) throws IOException, ServletException {

        //SecurityContext context = SecurityContextHolder.createEmptyContext();
        //context.setAuthentication(authResult);
        //SecurityContextHolder.setContext(context);
        //this.securityContextRepository.saveContext(context, request, response);
        if (this.logger.isDebugEnabled()) {
            this.logger.debug(LogMessage.format("Set SecurityContextHolder to %s", authResult));
        }
        //super.getRememberMeServices().loginSuccess(request, response, authResult);
        if (this.eventPublisher != null) {
            this.eventPublisher.publishEvent(new InteractiveAuthenticationSuccessEvent(authResult, this.getClass()));
        }

        super.getSuccessHandler().onAuthenticationSuccess(request, response, authResult);
    }

    protected String obtainUsername(UserDto userDto) throws IOException {
        return userDto.getUsername();
        //return request.getParameter(this.usernameParameter);
    }

    protected String obtainPassword(UserDto userDto) throws IOException {
        return userDto.getPassword();
    }

    protected void setDetails(HttpServletRequest request, UsernamePasswordAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }
}
