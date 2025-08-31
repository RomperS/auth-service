package com.olo.authservice.domain.command.users;

import com.olo.authservice.domain.models.permissions.Role;
import com.olo.authservice.domain.models.permissions.Title;

import java.util.Optional;

public record CreateUserCommand(
        String username,
        String email,
        String password,
        Role role,
        Title title
) {
    public static CreateUserCommand of(String username, String email, String password, Role role, Optional<Title> title) {
        Title finalTitle = title.orElseGet(role::getDefaultTitle);
        return new CreateUserCommand(username, email, password, role, finalTitle);
    }
}
