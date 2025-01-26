package com.realtrynna.spring_start.auth;

import com.realtrynna.spring_start.user.UserService;
import com.realtrynna.spring_start.user.model.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");

        /**
         * 토큰 검증과 사용자 조회
         */
        if (token == null || token.isEmpty()) {
            throw new ServletException("요청 헤더에 토큰이 존재하지 않거나 헤더의 속성이 올바르지 않습니다.");
        }

            /**
             * String.valueOf 객체나 값을 문자열로 변환
             */
        try {
            System.out.println("시큐리티 권한" + SecurityContextHolder.getContext().getAuthentication());

            String email = jwtUtil.getBodyFromToken(token).get("email").toString();

            System.out.println("인증 토큰" + getAuthentication(email));

            SecurityContextHolder.getContext().setAuthentication(getAuthentication(email));
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 이메일을 통해 사용자를 찾고 아이디와 패스워드로 인증 토큰 생성
     */
    private UsernamePasswordAuthenticationToken getAuthentication(String email) {
        Optional<User> user = userService.findUserByEmail(email);

        return user.map(
                value -> new UsernamePasswordAuthenticationToken(value.getId(), value.getPassword()))
            .orElse(null);

    }
}
