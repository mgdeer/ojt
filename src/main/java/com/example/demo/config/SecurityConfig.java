package com.example.demo.config;

import com.example.demo.Role;
import com.example.demo.SHA256;
import com.example.demo.jwt.*;
//import com.example.demo.jwt.JWTFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

//    private final AuthenticationConfiguration authenticationConfiguration;
//    private final JWTUtil jwtUtil;

//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
//
//        return configuration.getAuthenticationManager();
//    }

    @Bean
    public Sha256PasswordEncoder sha256PasswordEncoder() {

        return new Sha256PasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        //csrf disable
        http.csrf((auth) -> auth.disable())

        .exceptionHandling((auth)->auth.authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler))

                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .headers(headers -> headers
                        .frameOptions(frameOptions -> frameOptions.sameOrigin())
                )


//        //From 로그인 방식 disable
//        http
//                .formLogin((auth) -> auth.disable());
//
//        //http basic 인증 방식 disable
//        http
//                .httpBasic((auth) -> auth.disable());

        //경로별 인가 작업

                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/member/*").permitAll()
                        .requestMatchers("/admin").hasRole("ADMINISTRATOR")
                        .anyRequest().authenticated())
        .addFilterBefore(new JwtFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class);


//        http
//                .addFilterBefore(new JWTFilter(jwtUtil), LoginFilter.class);
//
//        http
//                .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration),jwtUtil), UsernamePasswordAuthenticationFilter.class);

        //세션 설정



        return http.build();
    }
}