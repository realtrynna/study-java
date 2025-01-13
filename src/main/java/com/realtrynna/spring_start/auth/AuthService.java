package com.realtrynna.spring_start.auth;

import com.realtrynna.spring_start.auth.model.request.LoginDto;
import com.realtrynna.spring_start.user.UserRepository;
import com.realtrynna.spring_start.user.model.User;
import java.util.Optional;
import javax.security.sasl.AuthenticationException;
import javax.swing.text.html.Option;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final Argon2PasswordEncoder passwordEncoder;
//    private final JwtUtil jwtUtil;

    public Boolean existUser(String email) {
        return userRepository.existsByEmail(email);
    }

    public void validateUser(LoginDto loginDto) throws AuthenticationException {
        Boolean existUser = existUser(loginDto.getEmail());

        final String[] password = new String[1];

        Optional<User> user = userRepository.findByEmail(loginDto.getEmail());
        user.ifPresent(info -> {
            password[0] = info.getPassword();
        });

        Boolean isPasswordMatched = passwordEncoder.matches(loginDto.getPassword(), password[0]);
        if (!existUser || !isPasswordMatched) throw new AuthenticationException("인증 실패");


    }
}
