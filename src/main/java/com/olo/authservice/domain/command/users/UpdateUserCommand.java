package com.olo.authservice.domain.command.users;

public record UpdateUserCommand(
        Long targetId,
        String username,
        String email,
        String password
) {
}
