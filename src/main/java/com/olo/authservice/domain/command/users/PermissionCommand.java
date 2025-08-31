package com.olo.authservice.domain.command.users;

import com.olo.authservice.domain.exceptions.permissions.InvalidPermissionValueException;
import com.olo.authservice.domain.models.permissions.Role;
import com.olo.authservice.domain.models.permissions.Title;

public record PermissionCommand(
        Title title,
        Role role
) {
    public Void validatePermission() {
        if (!title.getRole().equals(role)) {
            throw new InvalidPermissionValueException(
                    "Invalid permission: title does not correspond to role"
            );
        }
        return null;
    }
}

