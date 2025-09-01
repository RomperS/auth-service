package com.olo.authservice.domain.ports.inbound.validation;

import com.olo.authservice.domain.command.validation.AuthUserCommand;
import com.olo.authservice.domain.results.validation.AuthUserResult;

public interface LoginPort {
    AuthUserResult login(AuthUserCommand command);
}
