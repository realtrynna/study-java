package com.realtrynna.spring_start;

import com.realtrynna.spring_start.aop.TimeTraceAop;
import com.realtrynna.spring_start.repositories.BoardRepository;
import com.realtrynna.spring_start.services.BoardService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    public TimeTraceAop timeTraceAop() {
        return new TimeTraceAop();
    }
}
