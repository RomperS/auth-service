package com.olo.authservice.application.usecase.users;

import com.olo.authservice.domain.exceptions.users.UserNotFoundException;
import com.olo.authservice.domain.models.User;
import com.olo.authservice.domain.ports.inbound.users.GetUserByIdPort;
import com.olo.authservice.domain.ports.outbound.UserRepositoryPort;
import com.olo.authservice.domain.results.users.UserResult;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetUserByIdUseCase implements GetUserByIdPort {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public UserResult getUserById(Long userId) {
        User user = userRepositoryPort.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));

        return new UserResult(
                user.id(),
                user.username(),
                user.email(),
                user.accountLocked(),
                new UserResult.PermissionsResult(user.titles(), user.roles())
        );
    }
}
