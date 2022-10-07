package com.example.pmb.global.config.security;

import com.example.pmb.global.config.security.jwt.JwtJsonLoginFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurityConfig {

    /*private UserService userService; //userDetailService
    private PasswordEncoderConfig passwordEncoderConfig; //userDetailService*/
    /*private JwtAuthenticationFilter jwtAuthenticationFilter;*/
    private JwtJsonLoginFilter loginFilter;


    @Bean
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring().mvcMatchers(
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/api/v1/login" // 임시
        );
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf().disable()
            .formLogin().disable()
            .httpBasic().disable();
        http
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()
            .antMatchers("/test/**").permitAll()
            .antMatchers("/auth/**").permitAll()
            .antMatchers("/social/**").permitAll()
            .antMatchers("/exception/**").permitAll()
            .anyRequest().authenticated();

        //http.addFilterBefore(jwtIssueTokenFilter, UsernamePasswordAuthenticationFilter.class);
        //http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(loginFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

/*
    @Bean
    public AuthenticationManager authenticationManager(
        AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(passwordEncoderConfig.passwordEncoder());
        return authProvider;
    }
*/


}
