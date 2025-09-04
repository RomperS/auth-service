package com.olo.authservice.application.usecase.users;

import com.olo.authservice.domain.ports.inbound.users.GetAllUsersByTitlePort;
import com.olo.authservice.domain.ports.outbound.UserRepositoryPort;
import com.olo.authservice.domain.results.users.UserResult;
import com.olo.permissions.Title;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetAllUsersImpl implements GetAllUsersByTitlePort {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public List<UserResult> getAllUsersByTitle(Title title) {
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
