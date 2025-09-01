package com.olo.authservice.domain.results.permissions;

import com.olo.exceptions.permissions.InvalidPermissionValueException;
import com.olo.permissions.Role;
import com.olo.permissions.Title;

public record PermissionResult(
        Title title,
        Role role
) {
    public String getPermission() {
        if (title.getRole().equals(role)) {
            return role + ":" + title;
        }
        throw new InvalidPermissionValueException("Invalid permission: title does not correspond to role");
    }
}