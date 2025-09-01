package com.olo.authservice.domain.command.validation;

public record AuthUserCommand(
        String username,
        String password
) {
}
