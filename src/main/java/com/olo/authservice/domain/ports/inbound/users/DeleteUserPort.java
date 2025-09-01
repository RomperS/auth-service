package com.olo.authservice.domain.ports.inbound.users;

import com.olo.authservice.domain.results.users.UserResult;

public interface DeleteUserPort {
    UserResult deleteUserById(Long id);
}
