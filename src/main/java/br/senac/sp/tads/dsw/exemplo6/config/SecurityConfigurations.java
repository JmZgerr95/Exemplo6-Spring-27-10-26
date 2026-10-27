package br.senac.sp.tads.dsw.exemplo6.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration 
@EnableWebSecurity 
public class SecurityConfigurations {
    
    @Bean //Mostra que esse método participa do SpringBoot, então é necessário lidar com ele
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                //Necessário para permitir que o console H2 renderize a interface em frames
                .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                                                    .requestMatchers(
                                                        "/swagger-ui/**",
                                                        "/swagger-ui.html",
                                                        "/v3/api-docs/**",
                                                        "/h2-console/**",
                                                        "/auth/register" //Endpoint dessa API, os outros são padrões do swagger e do H2
                                                    ).permitAll().anyRequest().authenticated()
                )
                .build();
    } //Esse securityFilterChain possui muito código dentro dele.

    @Bean //Mostra que esse método participa do SpringBoot, então é necessário lidar com ele
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
