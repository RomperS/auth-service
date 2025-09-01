package com.olo.authservice.application.usecase.users;

import com.olo.authservice.domain.exceptions.users.UserNotFoundException;
import com.olo.authservice.domain.models.User;
import com.olo.authservice.domain.ports.inbound.users.DeleteUserPort;
import com.olo.authservice.domain.ports.outbound.UserRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteUserUseCase implements DeleteUserPort {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public void deleteUserById(Long id) {
        User user = userRepositoryPort.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        userRepositoryPort.delete(user.id());
    }
}
