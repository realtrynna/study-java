package com.realtrynna.spring_start.auth;

import com.realtrynna.spring_start.auth.model.request.LoginDto;
import com.realtrynna.spring_start.user.UserRepository;
import com.realtrynna.spring_start.user.model.User;
import java.util.Optional;
import javax.security.sasl.AuthenticationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final Argon2PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public String validateUser(LoginDto loginDto)
        throws Exception {
        Optional<User> user = userRepository.findByEmail(loginDto.getEmail());

        if (user.isEmpty() || !passwordEncoder.matches(loginDto.getPassword(), user.get().getPassword())) {
            throw new AuthenticationException("이메일 또는 비밀번호가 일치하지 않습니다.");
        }

        return jwtUtil.createToken(user.get());
    }

    public void validateToken(String token) throws Exception {
        Boolean result = jwtUtil.validateToken(token);
        String tokenBody = jwtUtil.getBodyFromToken(token).get("email").toString();

        System.out.println(tokenBody);
    }
}
