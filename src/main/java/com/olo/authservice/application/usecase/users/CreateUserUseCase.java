package com.olo.authservice.application.usecase.users;

import com.olo.authservice.domain.command.users.CreateUserCommand;
import com.olo.authservice.domain.exceptions.users.EmailAlreadyExistsException;
import com.olo.authservice.domain.exceptions.users.UsernameTakenException;
import com.olo.authservice.domain.models.User;
import com.olo.authservice.domain.ports.inbound.users.CreateUserPort;
import com.olo.authservice.domain.ports.outbound.PasswordEncoderPort;
import com.olo.authservice.domain.ports.outbound.UserRepositoryPort;
import com.olo.authservice.domain.results.users.UserResult;
import com.olo.permissions.Role;
import com.olo.permissions.Title;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class CreateUserUseCase implements CreateUserPort {

    private final UserRepositoryPort userRepositoryPort;
    private final PasswordEncoderPort passwordEncoderPort;

    @Override
    public UserResult createUser(CreateUserCommand command) {
        CreateUserCommand createUserCommand = CreateUserCommand.of(command);

        if (userRepositoryPort.existByUsername(createUserCommand.username())){
            throw new UsernameTakenException("Username is already taken");
        }
        if (userRepositoryPort.existByEmail(createUserCommand.email())){
            throw new EmailAlreadyExistsException(
                    String.format("Email '%s' is already registered.", createUserCommand.email())
            );
        }

        List<Role> roles = new ArrayList<>();
        roles.add(createUserCommand.role());
        List<Title> titles = new ArrayList<>();
        titles.add(createUserCommand.title());

        User user = new User(
                null,
                createUserCommand.username(),
                createUserCommand.email(),
                passwordEncoderPort.encode(createUserCommand.password()),
                false,
                roles,
                titles
        );

        User savedUser = userRepositoryPort.save(user);

        return new UserResult(
                savedUser.id(),
                savedUser.username(),
                savedUser.email(),
                savedUser.accountLocked(),
                new UserResult.PermissionsResult(savedUser.titles(), savedUser.roles())
        );
    }
}
