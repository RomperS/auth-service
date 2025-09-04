package com.olo.authservice.application.usecase.validation;

import com.olo.authservice.application.service.TokenService;
import com.olo.authservice.application.service.UserService;
import com.olo.authservice.domain.command.users.CreateUserCommand;
import com.olo.authservice.domain.ports.inbound.validation.SignupPort;
import com.olo.authservice.domain.results.tokens.AccessTokenResult;
import com.olo.authservice.domain.results.tokens.TokenResult;
import com.olo.authservice.domain.results.users.UserResult;
import com.olo.authservice.domain.results.validation.AuthUserResult;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SignupImpl implements SignupPort {

    private final UserService userService;
    private final TokenService tokenService;

    @Override
    public AuthUserResult signup(CreateUserCommand command) {

        UserResult userResult = userService.createUser(command);

        TokenResult tokenResult = tokenService.createToken(userResult.username());

        AccessTokenResult accessTokenResult = tokenService.generateAccessToken(tokenResult.refreshToken());

        return new AuthUserResult(tokenResult.refreshToken(), tokenResult.expiredAt(), accessTokenResult);
    }
}
