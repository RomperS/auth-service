package com.olo.authservice.application.usecase.users;

import com.olo.authservice.common.anotations.CustomTransactional;
import com.olo.authservice.domain.command.users.UpdateUserCommand;
import com.olo.authservice.domain.exceptions.users.EmailAlreadyExistsException;
import com.olo.authservice.domain.exceptions.users.UserNotFoundException;
import com.olo.authservice.domain.exceptions.users.UsernameTakenException;
import com.olo.authservice.domain.models.User;
import com.olo.authservice.domain.ports.inbound.users.UpdateUserPort;
import com.olo.authservice.domain.ports.outbound.PasswordEncoderPort;
import com.olo.authservice.domain.ports.outbound.UserRepositoryPort;
import com.olo.authservice.domain.results.users.UserResult;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateUserImpl implements UpdateUserPort {

    private final UserRepositoryPort userRepositoryPort;
    private final PasswordEncoderPort passwordEncoderPort;

    @Override
    @CustomTransactional
    public UserResult updateUser(UpdateUserCommand command) {
        User user = userRepositoryPort.findById(command.userId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        String newUsername = command.username();
        if (newUsername != null && !newUsername.equals(user.username())) {
            if (userRepositoryPort.existByUsername(newUsername)) {
                throw new UsernameTakenException("Username is already taken");
            }
        } else {
            newUsername = user.username();
        }

        String newEmail = command.email();
        if (newEmail != null && !newEmail.equals(user.email())) {
            if (userRepositoryPort.existByEmail(newEmail)) {
                throw new EmailAlreadyExistsException(
                        String.format("Email '%s' is already registered.", newEmail)
                );
            }
        } else {
            newEmail = user.email();
        }

        String newPassword = command.password();
        if (newPassword != null) {
            newPassword = passwordEncoderPort.encode(newPassword);
        } else {
            newPassword = user.password();
        }

        User updatedUser = new User(
                user.id(),
                newUsername,
                newEmail,
                newPassword,
                user.accountLocked(),
                user.roles(),
                user.titles()
        );

        User savedUser = userRepositoryPort.save(updatedUser);

        return new UserResult(
                savedUser.id(),
                savedUser.username(),
                savedUser.email(),
                savedUser.accountLocked(),
                new UserResult.PermissionsResult(savedUser.titles(), savedUser.roles())
        );
    }
}