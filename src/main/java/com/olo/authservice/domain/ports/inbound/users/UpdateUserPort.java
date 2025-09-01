package com.olo.authservice.domain.ports.inbound.users;

import com.olo.authservice.domain.command.users.UpdateUserCommand;
import com.olo.authservice.domain.results.users.UserResult;

public interface UpdateUserPort {
    UserResult updateUser(UpdateUserCommand command);
}
