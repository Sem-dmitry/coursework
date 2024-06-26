package com.example.kursov.configs;

import com.example.kursov.models.UserAuthority;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(expressionInterceptUrlRegistry ->
                        expressionInterceptUrlRegistry
                                .requestMatchers("/registration", "/login").permitAll()
                                .requestMatchers(HttpMethod.GET, "/pictures/**").hasAnyAuthority(
                                        UserAuthority.VIEW_PICTURES.getAuthority(),
                                        UserAuthority.CREATE_PICTURES.getAuthority(),
                                        UserAuthority.SAVE_PICTURES.getAuthority(),
                                        UserAuthority.MANAGE_PICTURES.getAuthority()
                                )
                                .requestMatchers(HttpMethod.POST, "/pictures/predict").hasAnyAuthority(
                                        UserAuthority.CREATE_PICTURES.getAuthority(),
                                        UserAuthority.SAVE_PICTURES.getAuthority(),
                                        UserAuthority.MANAGE_PICTURES.getAuthority()
                                )
                                .requestMatchers(HttpMethod.POST, "/pictures/save").hasAnyAuthority(
                                        UserAuthority.SAVE_PICTURES.getAuthority(),
                                        UserAuthority.MANAGE_PICTURES.getAuthority()
                                )
                                .anyRequest().hasAuthority(UserAuthority.MANAGE_PICTURES.getAuthority())
                )

                .formLogin(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
