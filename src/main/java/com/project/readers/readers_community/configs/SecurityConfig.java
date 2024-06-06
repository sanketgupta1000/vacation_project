package com.project.readers.readers_community.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig
{

    private final UserDetailsService customUserDetailsService;

    // DI
    public SecurityConfig(UserDetailsService customUserDetailsService)
    {
        this.customUserDetailsService = customUserDetailsService;
    }

    // security config bean
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // need to disable session and csrf since stateless
                .csrf(csrf->csrf.disable())
                .authorizeHttpRequests(auth->{
                    // all requests authenticated for now
                        auth
                            .anyRequest().authenticated();
                })
                // custom user details service
                .userDetailsService(customUserDetailsService)
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults())
                .build();
    }

}
