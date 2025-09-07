package com.olo.authservice.infrastructure.dtos.request;

import com.olo.permissions.Role;
import com.olo.permissions.Title;

public record CreateUserRequestDto(
        String username,
        String email,
        String password,
        Role role,
        Title title
) {
}
