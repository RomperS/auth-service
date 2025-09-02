package com.olo.authservice.domain.ports.inbound.users;

import com.olo.authservice.domain.results.users.UserResult;

import java.util.List;

public interface GetAllUsersPort {
    List<UserResult> getAllUsers();
}
