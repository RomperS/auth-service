package com.olo.authservice.domain.ports.inbound.validation;

import com.olo.authservice.domain.command.validation.AuthUserCommand;

public interface LogoutPort {
    void logout(AuthUserCommand command);
}
