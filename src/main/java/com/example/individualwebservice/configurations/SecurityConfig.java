package com.example.individualwebservice.configurations;

import com.example.individualwebservice.converters.JwtAuthConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig {

    private JwtAuthConverter jwtAuthConverter;

    @Autowired
    public SecurityConfig(JwtAuthConverter jwtAuthConverter) {
        this.jwtAuthConverter = jwtAuthConverter;
    }

    @Bean
    protected SessionAuthenticationStrategy strategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf->csrf.disable())

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/newpost").authenticated()
                        .requestMatchers("/api/updatepost/**").authenticated()
                        .requestMatchers("/api/deletepost/**").authenticated()

                        .requestMatchers("/api/users").hasRole("bloggheaven_ADMIN")
                        .requestMatchers("/api/users/**").hasRole("bloggheaven_ADMIN")


                        .anyRequest()
                        .permitAll()
                );

        http
                .oauth2ResourceServer(ors -> ors
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(jwtAuthConverter))
                );

        http
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(STATELESS)
                );

        return http.build();
    }


}
