package com.olo.authservice.domain.ports.inbound.validation;

import com.olo.authservice.domain.command.users.CreateUserCommand;
import com.olo.authservice.domain.results.validation.AuthUserResult;

public interface SignupPort {
    AuthUserResult signup(CreateUserCommand command);
}
