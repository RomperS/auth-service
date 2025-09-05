package com.olo.authservice.application.usecase.permissions;

import com.olo.authservice.common.anotations.CustomTransactional;
import com.olo.authservice.domain.command.permissions.PermissionCommand;
import com.olo.authservice.domain.exceptions.users.UserNotFoundException;
import com.olo.authservice.domain.models.User;
import com.olo.authservice.domain.ports.inbound.permissions.AssignUserPermissionsPort;
import com.olo.authservice.domain.ports.outbound.UserRepositoryPort;
import com.olo.authservice.domain.results.users.UserResult;
import com.olo.exceptions.permissions.AssignmentNotAllowedException;
import com.olo.exceptions.permissions.RoleNotPresentException;
import com.olo.permissions.Role;
import com.olo.permissions.Title;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class AssignUserPermissionsImpl implements AssignUserPermissionsPort {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    @CustomTransactional
    public UserResult assignUserPermissions(PermissionCommand command, Long userId) {
        User user = userRepositoryPort.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (command.role() == Role.ADMIN || command.role() == Role.SUPER_ADMIN) {
            throw new AssignmentNotAllowedException("Cannot assign ADMIN or SUPER_ADMIN roles");
        }

        command.validatePermission();

        List<Role> updatedRoles = Stream.concat(user.roles().stream(),
                        (command.role().equals(Role.AUXILIARY_ADMIN)) ? Stream.of(command.role()) : Stream.empty())
                .distinct()
                .collect(Collectors.toList());

        List<Title> updatedTitles = Stream.concat(user.titles().stream(), Stream.of(command.title()))
                .distinct()
                .collect(Collectors.toList());

        if (updatedTitles.stream().noneMatch(title -> title.getRole().equals(command.title().getRole()))) {
            throw new RoleNotPresentException("Role not present");
        }

        User PromotedUser = new User(
                user.id(),
                user.username(),
                user.email(),
                user.password(),
                user.accountLocked(),
                updatedRoles,
                updatedTitles
        );

        User savedUser = userRepositoryPort.save(PromotedUser);
        return new UserResult(
                savedUser.id(),
                savedUser.username(),
                savedUser.email(),
                savedUser.accountLocked(),
                new UserResult.PermissionsResult(savedUser.titles(), savedUser.roles())
        );
    }
}
