package com.olo.authservice.domain.results.users;

import com.olo.permissions.Role;
import com.olo.permissions.Title;

import java.util.List;

public record UserResult(
        Long id,
        String username,
        String email,
        String password,
        Boolean accountLocked,
        PermissionsResult permissions
) {
    public record PermissionsResult(
            List<Title> titles,
            List<Role> roles
    ) {
        public List<String> getPermissions() {
            return roles.stream()
                    .flatMap(role -> titles.stream()
                            .filter(title -> title.getRole().equals(role))
                            .map(title -> role + ":" + title))
                    .toList();
        }
    }
}

