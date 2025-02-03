package com.realtrynna.spring_start.config.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import javax.lang.model.type.ErrorType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    public CustomAuthenticationEntryPoint(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException authException) {
        System.out.println("인증 예외" + authException);
        exceptionHandler(res);
    }

    public void exceptionHandler(HttpServletResponse res) {
        res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        res.setContentType("application/json;charset=utf-8");

        Map<String, Object> errorRes = new HashMap<>();
        errorRes.put("status", HttpServletResponse.SC_UNAUTHORIZED);
        errorRes.put("error", "Unauthorized");
        errorRes.put("message", "인증되지 않은 사용자는 해당 리소스에 접근할 수 없습니다.");
        errorRes.put("path", res.getHeader("Location"));

        try {
            res.getWriter().write(objectMapper.writeValueAsString(errorRes));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
