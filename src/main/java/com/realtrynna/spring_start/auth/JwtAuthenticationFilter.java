package com.realtrynna.spring_start.auth;

import com.realtrynna.spring_start.user.UserService;
import com.realtrynna.spring_start.user.model.User;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 요청이 컨트롤러로 도달하기 전 요청 헤더에 있는 토큰을 검증합니다.
 */
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException {
        String token = request.getHeader("Authorization");

        if (token == null || token.isEmpty()) {
            throw new ServletException("요청 헤더에 토큰이 존재하지 않거나 헤더의 속성이 올바르지 않습니다.");
        }

        try {
            /**
             * 토큰 검증
             */
            jwtUtil.validateToken(token);

            /**
             * 토큰 페이로드에서 사용자 정보 추출
             */
            String email = jwtUtil.getBodyFromToken(token).get("email").toString();

            System.out.println("필터 인증 여부" + SecurityContextHolder.getContext().getAuthentication());
            SecurityContextHolder.getContext().setAuthentication(getAuthentication(email));
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            throw new JwtException(e.getMessage());
        }

    }

    /**
     * 이메일을 통해 사용자를 찾고 아이디와 패스워드로 인증 토큰 생성
     */
    private UsernamePasswordAuthenticationToken getAuthentication(String email) {
//        Optional<User> user = userService.findUserByEmail(email);
//
//        return user.map(
//                value -> new UsernamePasswordAuthenticationToken(
//                    value,
//                    Collections.singletonList(new SimpleGrantedAuthority("normal"))
//                ))
//            .orElse(null);
        return new UsernamePasswordAuthenticationToken(
            "value",
            Collections.singletonList(new SimpleGrantedAuthority("normal")));
    }
}
