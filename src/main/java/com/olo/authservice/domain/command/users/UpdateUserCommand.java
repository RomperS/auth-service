package com.olo.authservice.domain.command.users;

public record UpdateUserCommand(
        Long userId,
        String username,
        String email,
        String password
) {
}
