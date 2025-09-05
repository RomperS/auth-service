package com.olo.authservice.application.usecase.users;

import com.olo.authservice.domain.ports.inbound.users.GetAllUsersPort;
import com.olo.authservice.domain.ports.outbound.UserRepositoryPort;
import com.olo.authservice.domain.results.users.UserResult;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetAllUsersImpl implements GetAllUsersPort {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public List<UserResult> getAllUsers() {
        return userRepositoryPort.findAll().stream()
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
