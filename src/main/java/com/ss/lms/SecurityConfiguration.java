package com.ss.lms;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfiguration {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails john = User.builder()
                .username("john")
                .password("{noop}password")
                .roles("ADMIN", "EMPLOYEE", "MANAGER")
                .build();

        return new InMemoryUserDetailsManager(john);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET, "/api/user/").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/user/**").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/api/user/").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/user/").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/user/**").hasRole("ADMIN")
        );

        http.httpBasic(withDefaults());
        http.cors(withDefaults());
        http.csrf(AbstractHttpConfigurer::disable);


        return http.build();
    }
}
