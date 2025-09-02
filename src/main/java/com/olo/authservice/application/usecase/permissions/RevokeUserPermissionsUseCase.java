package com.olo.authservice.application.usecase.permissions;

import com.olo.authservice.domain.command.permissions.PermissionCommand;
import com.olo.authservice.domain.exceptions.users.UserNotFoundException;
import com.olo.authservice.domain.models.User;
import com.olo.authservice.domain.ports.inbound.permissions.RevokeUserPermissionsPort;
import com.olo.authservice.domain.ports.outbound.UserRepositoryPort;
import com.olo.authservice.domain.results.users.UserResult;
import com.olo.exceptions.permissions.AssignmentNotAllowedException;
import com.olo.exceptions.permissions.DefaultTitleRemovalException;
import com.olo.permissions.Role;
import com.olo.permissions.Title;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class RevokeUserPermissionsUseCase implements RevokeUserPermissionsPort {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public UserResult revokeUserPermissions(PermissionCommand command, Long userId) {
        User user = userRepositoryPort.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (command.role() == Role.ADMIN || command.role() == Role.SUPER_ADMIN) {
            throw new AssignmentNotAllowedException("Cannot revoke ADMIN or SUPER_ADMIN roles");
        }

        command.validatePermission();

        List<Role> updatedRoles = user.roles().stream()
                .filter(r -> !(r.equals(command.role()) && r.equals(Role.AUXILIARY_ADMIN)))
                .toList();

        boolean isDefaultTitle = updatedRoles.stream()
                .map(Role::getDefaultTitle)
                .anyMatch(defaultTitle -> defaultTitle.equals(command.title()));

        if (isDefaultTitle) {
            throw new DefaultTitleRemovalException("Default title removal not allowed");
        }

        List<Title> updatedTitles = user.titles().stream()
                .filter(title -> !title.equals(command.title()))
                .collect(Collectors.toList());

        User RevokedUser = new User(
                user.id(),
                user.username(),
                user.email(),
                user.password(),
                user.accountLocked(),
                updatedRoles,
                updatedTitles
        );

        User savedUser = userRepositoryPort.save(RevokedUser);
        return new UserResult(
                savedUser.id(),
                savedUser.username(),
                savedUser.email(),
                savedUser.accountLocked(),
                new UserResult.PermissionsResult(savedUser.titles(), savedUser.roles())
        );
    }
}
