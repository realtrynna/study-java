package com.realtrynna.spring_start;

import com.realtrynna.spring_start.auth.JwtAuthenticationFilter;
import com.realtrynna.spring_start.auth.JwtUtil;
import com.realtrynna.spring_start.config.auth.CustomAuthenticationEntryPoint;
import com.realtrynna.spring_start.user.UserService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig  {
    private final List<String> AUTH_WHITELIST = List.of(
        "/login",
        "/update"
    );
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final JwtUtil jwtUtil;

    @Bean
    public Argon2PasswordEncoder passwordEncoder() {
        return new Argon2PasswordEncoder(
            16,
            32,
            1,
            65536,
            3
        );
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(Customizer.withDefaults());

        http.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(
            SessionCreationPolicy.STATELESS));

        http.formLogin(AbstractHttpConfigurer::disable);
        http.httpBasic(AbstractHttpConfigurer::disable);

        http.addFilterBefore(new JwtAuthenticationFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

        http.authorizeHttpRequests(authorizeRequests ->
            authorizeRequests.requestMatchers("/login").permitAll()
                .anyRequest().authenticated()
        );

        return http.build();
    }
}
