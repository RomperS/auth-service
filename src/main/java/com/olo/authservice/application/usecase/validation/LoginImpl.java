package com.olo.authservice.application.usecase.validation;

import com.olo.authservice.application.service.TokenService;
import com.olo.authservice.application.service.UserService;
import com.olo.authservice.domain.command.validation.AuthUserCommand;
import com.olo.authservice.domain.exceptions.users.UserNotFoundException;
import com.olo.authservice.domain.exceptions.validation.InvalidCredentialsException;
import com.olo.authservice.domain.models.Token;
import com.olo.authservice.domain.models.User;
import com.olo.authservice.domain.ports.inbound.validation.LoginPort;
import com.olo.authservice.domain.ports.outbound.PasswordEncoderPort;
import com.olo.authservice.domain.ports.outbound.UserRepositoryPort;
import com.olo.authservice.domain.results.tokens.AccessTokenResult;
import com.olo.authservice.domain.results.tokens.TokenResult;
import com.olo.authservice.domain.results.users.UserResult;
import com.olo.authservice.domain.results.validation.AuthUserResult;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class LoginImpl implements LoginPort {

    private final PasswordEncoderPort passwordEncoderPort;
    private final UserRepositoryPort userRepositoryPort;
    private final TokenService tokenService;

    @Override
    public AuthUserResult login(AuthUserCommand command) {
        User user = userRepositoryPort.findByUsername(command.username()).orElseThrow(() -> new UserNotFoundException("User not found"));

        if (!passwordEncoderPort.matches(command.password(), user.password())){
            throw new InvalidCredentialsException("Invalid credentials");
        }

        TokenResult token = tokenService.createToken(user.username());

        List<TokenResult> oldTokens = tokenService.getActiveUserToken(user.username());

        for (TokenResult oldToken : oldTokens) {
            tokenService.revokeToken(oldToken.jti());
        }

        AccessTokenResult accessToken = tokenService.generateAccessToken(token.refreshToken());

        return new AuthUserResult(token.refreshToken(), token.expiredAt(), accessToken);
    }
}
