package com.example.pmb.global.config.security;

import com.example.pmb.domain.common.BeanProvider;
import com.example.pmb.global.config.security.jwt.JwtAuthenticationFilter;
import com.example.pmb.global.config.security.jwt.JwtJsonLoginAuthFailureHandler;
import com.example.pmb.global.config.security.jwt.JwtJsonLoginAuthManager;
import com.example.pmb.global.config.security.jwt.JwtJsonLoginAuthSuccessHandler;
import com.example.pmb.global.config.security.jwt.JwtJsonLoginFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity(debug = true)
public class SpringSecurityConfig {

    /*private UserService userService; //userDetailService
    private PasswordEncoderConfig passwordEncoderConfig; //userDetailService*/
    /*private JwtAuthenticationFilter jwtAuthenticationFilter;*/

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
        http.addFilterBefore(jwtJsonLoginFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    private JwtJsonLoginFilter jwtJsonLoginFilter() throws Exception{
        ApplicationContext applicationContext = BeanProvider.getApplicationContext();
        JwtJsonLoginFilter jwtJsonLoginFilter = applicationContext.getBean(JwtJsonLoginFilter.class);
        return jwtJsonLoginFilter;
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
