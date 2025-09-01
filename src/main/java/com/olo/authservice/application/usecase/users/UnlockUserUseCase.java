package com.olo.authservice.application.usecase.users;

import com.olo.authservice.domain.exceptions.users.UserNotFoundException;
import com.olo.authservice.domain.models.User;
import com.olo.authservice.domain.ports.inbound.users.UnlockUserPort;
import com.olo.authservice.domain.ports.outbound.UserRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UnlockUserUseCase implements UnlockUserPort {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public void unlockUser(Long userId) {
        User user = userRepositoryPort.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));

        if (user.accountLocked()){
            User unlockedUser = new User(
                    user.id(),
                    user.username(),
                    user.email(),
                    user.password(),
                    false,
                    user.roles(),
                    user.titles()
            );

            userRepositoryPort.save(unlockedUser);
        }
    }
}
