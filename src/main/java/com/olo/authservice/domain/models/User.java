package com.olo.authservice.domain.models;

import com.olo.authservice.domain.models.permissions.Role;
import com.olo.authservice.domain.models.permissions.Title;

import java.util.List;

public record User(
        Long id,
        String username,
        String email,
        String password,
        Boolean accountLocked,
        List<Role> roles,
        List<Title> titles
) {
}