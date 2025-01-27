package com.realtrynna.spring_start;

import com.realtrynna.spring_start.auth.JwtAuthenticationFilter;
import com.realtrynna.spring_start.auth.JwtUtil;
import com.realtrynna.spring_start.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FilterConfig {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Bean
    public FilterRegistrationBean<JwtAuthenticationFilter> filterRegistrationBean() {
        FilterRegistrationBean<JwtAuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new JwtAuthenticationFilter(userService, jwtUtil));
        registrationBean.addUrlPatterns("/update");
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
