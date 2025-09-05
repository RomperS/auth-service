package com.olo.authservice.application.service;

import com.olo.authservice.application.usecase.validation.*;
import com.olo.authservice.domain.command.users.CreateUserCommand;
import com.olo.authservice.domain.command.validation.AuthUserCommand;
import com.olo.authservice.domain.ports.inbound.validation.*;
import com.olo.authservice.domain.results.validation.AuthUserResult;
import com.olo.authservice.domain.results.validation.TokenPermissionsResult;
import com.olo.authservice.domain.results.validation.ValidateTokenResult;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ValidationService implements LoginPort, LogoutPort, SignupPort, ValidateTokenPort, GetPermissionsByTokenPort {

    private final LoginImpl loginImpl;
    private final LogoutImpl logoutImpl;
    private final SignupImpl signupImpl;
    private final ValidateTokenImpl validateTokenImpl;
    private final GetPermissionsByTokenImpl getPermissionsByTokenImpl;

    @Override
    public AuthUserResult login(AuthUserCommand command) {
        return  loginImpl.login(command);
    }

    @Override
    public void logout(AuthUserCommand command) {
        logoutImpl.logout(command);
    }

    @Override
    public AuthUserResult signup(CreateUserCommand command) {
        return signupImpl.signup(command);
    }

    @Override
    public ValidateTokenResult validateToken(String token) {
        return validateTokenImpl.validateToken(token);
    }

    @Override
    public TokenPermissionsResult getPermissionsByToken(String token) {
        return getPermissionsByTokenImpl.getPermissionsByToken(token);
    }
}
