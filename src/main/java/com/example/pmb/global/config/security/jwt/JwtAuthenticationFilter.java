package com.example.pmb.global.config.security.jwt;

import com.example.pmb.domain.auth.component.UserService;
import com.nimbusds.jose.JOSEException;
import java.io.IOException;
import java.text.ParseException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

//before usernamepasswordauthenticationFilter
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
//https://connect2id.com/products/nimbus-jose-jwt/examples/jwt-with-eddsa
//https://llshl.tistory.com/28
//https://webfirewood.tistory.com/115
    private JwtTokenProvider jwtTokenProvider;
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {

        String token = jwtTokenProvider.resolveToken(request);
        // 유효한 토큰인지 확인합니다.
        if (token != null && jwtTokenProvider.validateToken(token)) {
            // 토큰이 유효하면 토큰으로부터 인증을 생성하여 받아옵니다.
            Authentication auth = null;

            try {
                auth = jwtTokenProvider.getAuthentication(token);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            } catch (JOSEException e) {
                throw new RuntimeException(e);
            }

            // SecurityContext 에 Authentication 객체를 저장합니다.
            SecurityContextHolder.getContext().setAuthentication(auth);
            System.out.println("토큰 유효하다");
        }
        filterChain.doFilter(request, response);

    }


}
