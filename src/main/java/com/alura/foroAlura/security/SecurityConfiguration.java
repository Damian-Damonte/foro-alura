package com.alura.foroAlura.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(requests -> requests
//                                .requestMatchers(HttpMethod.POST, "/bookings").authenticated()
//                                .requestMatchers(HttpMethod.POST, "/ratings").authenticated()
//                                .requestMatchers(HttpMethod.POST, "/usuarios/favoritos").authenticated()
//                                .requestMatchers(HttpMethod.GET, "/usuarios/{id}").authenticated()
//                                .requestMatchers("/usuarios/**").hasRole("ADMIN")
                                .requestMatchers("/auth/**").permitAll()
                                .anyRequest().authenticated()
//                                .requestMatchers(HttpMethod.GET).permitAll()
//                                .anyRequest().hasRole("ADMIN")
//                        .anyRequest().permitAll()
                )
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .cors()
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
