package com.olo.authservice.application.usecase.validation;

import com.olo.authservice.application.service.TokenService;
import com.olo.authservice.domain.command.validation.AuthUserCommand;
import com.olo.authservice.domain.exceptions.users.UserNotFoundException;
import com.olo.authservice.domain.exceptions.validation.InvalidCredentialsException;
import com.olo.authservice.domain.models.User;
import com.olo.authservice.domain.ports.inbound.validation.LogoutPort;
import com.olo.authservice.domain.ports.outbound.PasswordEncoderPort;
import com.olo.authservice.domain.ports.outbound.UserRepositoryPort;
import com.olo.authservice.domain.results.tokens.TokenResult;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class LogoutImpl implements LogoutPort {

    private final TokenService tokenService;
    private final UserRepositoryPort userRepositoryPort;
    private final PasswordEncoderPort passwordEncoderPort;

    @Override
    public void logout(AuthUserCommand command) {
        User user = userRepositoryPort.findByUsername(command.username()).orElseThrow(() -> new UserNotFoundException("User not found"));

        if (!passwordEncoderPort.matches(command.password(), user.password())){
            throw new InvalidCredentialsException("Invalid credentials");
        }

        TokenResult token = tokenService.createToken(user.username());

        List<TokenResult> oldTokens = tokenService.getActiveUserToken(user.username());

        for (TokenResult oldToken : oldTokens) {
            tokenService.revokeToken(oldToken.jti());
        }
    }
}
