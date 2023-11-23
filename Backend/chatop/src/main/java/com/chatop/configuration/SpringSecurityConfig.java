package com.chatop.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http)
    throws Exception {
    return http
      .authorizeHttpRequests(auth -> {
        auth
          .requestMatchers(HttpMethod.GET, "/**")
          .permitAll()
          .requestMatchers(HttpMethod.POST, "/**")
          .permitAll()
          .anyRequest()
          .permitAll();
      })
      .csrf(csrf -> csrf.disable())
      .build();
  }
}
