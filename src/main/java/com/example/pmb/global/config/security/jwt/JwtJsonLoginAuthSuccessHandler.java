package com.example.pmb.global.config.security.jwt;

import com.example.pmb.domain.auth.entity.User;
import com.nimbusds.jose.JOSEException;
import java.io.IOException;
import java.security.Principal;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtJsonLoginAuthSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtTokenProvider jwtTokenProvider;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authResult) throws IOException, ServletException {

        String user = (String)authResult.getPrincipal();

        String jwtToken = null;
        try {
            jwtToken = jwtTokenProvider.createToken(user,null);
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Bearer " + jwtToken);

        response.getWriter().write("Bearer " + jwtToken);
        response.getWriter().flush();
    }
}
