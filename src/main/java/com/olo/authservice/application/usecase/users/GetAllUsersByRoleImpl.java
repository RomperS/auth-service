package com.olo.authservice.application.usecase.users;

import com.olo.authservice.domain.ports.inbound.users.GetAllUsersByRolePort;
import com.olo.authservice.domain.ports.outbound.UserRepositoryPort;
import com.olo.authservice.domain.results.users.UserResult;
import com.olo.permissions.Role;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetAllUsersByRoleImpl implements GetAllUsersByRolePort {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public List<UserResult> getAllUsersByRole(Role role) {
        return userRepositoryPort.findUsersByRole(role)
                .stream()
                .map(user ->
                        new UserResult(
                                user.id(),
                                user.username(),
                                user.email(),
                                user.accountLocked(),
                                new UserResult.PermissionsResult(user.titles(), user.roles())
                        )
                )
                .toList();
    }
}
