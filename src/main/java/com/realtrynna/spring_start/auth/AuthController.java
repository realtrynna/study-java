package com.realtrynna.spring_start.auth;

import com.realtrynna.spring_start.auth.model.request.LoginDto;
import com.realtrynna.spring_start.common.exception.ApiResponse;
import jakarta.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.security.sasl.AuthenticationException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("login")
    public ApiResponse<String> login(@Valid @RequestBody LoginDto loginDto)
        throws AuthenticationException, NoSuchAlgorithmException, InvalidKeySpecException {
        authService.validateUser(loginDto);

        return ApiResponse.success("success");
    }
}
