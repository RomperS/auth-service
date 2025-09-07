package com.olo.authservice.domain.command.users;

import com.olo.authservice.domain.exceptions.users.SuperAdminCreationNotAllowedException;
import com.olo.exceptions.permissions.InvalidPermissionValueException;
import com.olo.permissions.Role;
import com.olo.permissions.Title;

public record CreateUserCommand(
        String username,
        String email,
        String password,
        Role role,
        Title title
) {
    public static CreateUserCommand of(CreateUserCommand command) {
        if (command.role.equals(Role.SUPER_ADMIN)) {
            throw new SuperAdminCreationNotAllowedException("Creating a user with super-administrator permissions is not allowed.");
        }
        Title finalTitle = (command.title != null) ? command.title : command.role.getDefaultTitle();
        if (!finalTitle.getRole().equals(command.role)) {
            throw new InvalidPermissionValueException("Title and role do not match, invalid values");
        }
        return new CreateUserCommand(command.username, command.email, command.password, command.role, finalTitle);
    }

}
