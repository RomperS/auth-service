package com.olo.authservice.domain.ports.inbound.users;

import com.olo.authservice.domain.command.users.CreateUserCommand;
import com.olo.authservice.domain.results.users.UserResult;

public interface CreateUserPort {
    UserResult createUser(CreateUserCommand command);
}
