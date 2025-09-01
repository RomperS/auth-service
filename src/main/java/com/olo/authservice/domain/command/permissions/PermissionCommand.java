package com.olo.authservice.domain.command.permissions;

import com.olo.exceptions.permissions.InvalidPermissionValueException;
import com.olo.permissions.Role;
import com.olo.permissions.Title;

public record PermissionCommand(
        Title title,
        Role role
) {
    public void validatePermission() {
        if (!title.getRole().equals(role)) {
            throw new InvalidPermissionValueException(
                    "Invalid permission: title does not correspond to role"
            );
        }
    }
}

