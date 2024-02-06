package com.nextevent.security;


import com.nextevent.entity.Role;
import com.nextevent.jwt.JwtAuthenticationFilter;
import com.nextevent.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final UserService userService;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request.requestMatchers("api/v1/register/**").permitAll()
                        .requestMatchers("/api/v1/ticket-category/get-by-eventId").permitAll()
                        .requestMatchers("/api/v1/event/all").permitAll()
                        .requestMatchers("/api/v1/event/get-by-id").permitAll()
                        .requestMatchers("/api/v1/event/update").hasAnyAuthority(Role.ADMIN.name())
                        .requestMatchers("/api/v1/event/save").hasAnyAuthority(Role.ADMIN.name())
                        .requestMatchers("/api/v1/ticket-category/save").hasAnyAuthority(Role.ADMIN.name())
                        .requestMatchers("/api/v1/organizer/**").hasAnyAuthority(Role.ADMIN.name())
                        .requestMatchers("/api/v1/ticket-category/save").hasAnyAuthority(Role.ADMIN.name())


                        .requestMatchers("/api/v1/ticket-category/**").hasAnyAuthority(Role.USER.name())
                        .requestMatchers("/api/v1/event/get-by-id").hasAnyAuthority(Role.USER.name())

                        .requestMatchers("/api/v1/customer/**").hasAnyAuthority(Role.USER.name())
                        .requestMatchers("/api/v1/cart/**").hasAnyAuthority(Role.USER.name())
                        .requestMatchers("/api/v1/ticket/**").hasAnyAuthority(Role.USER.name())

                        .anyRequest().authenticated())

                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider()).addFilterBefore(
                        jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class
                );
        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService.userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)
        throws Exception{
        return configuration.getAuthenticationManager();
    }
}
