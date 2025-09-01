package com.olo.authservice.application.usecase.users;

import com.olo.authservice.domain.exceptions.users.SuperUserActionNotAllowedException;
import com.olo.authservice.domain.exceptions.users.UserNotFoundException;
import com.olo.authservice.domain.models.User;
import com.olo.authservice.domain.ports.inbound.users.LockUserPort;
import com.olo.authservice.domain.ports.outbound.UserRepositoryPort;
import com.olo.permissions.Role;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LockUserUseCase implements LockUserPort {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public void lockUser(Long userId) {
        User user = userRepositoryPort.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));

        if (user.roles().contains(Role.ADMIN)) {
            throw new SuperUserActionNotAllowedException("User is not allowed to lock admin");
        }

        if (!user.accountLocked()){
            User unlockedUser = new User(
                    user.id(),
                    user.username(),
                    user.email(),
                    user.password(),
                    true,
                    user.roles(),
                    user.titles()
            );

            userRepositoryPort.save(unlockedUser);
        }
    }
}
