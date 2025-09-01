package com.olo.authservice.domain.ports.inbound.users;

import com.olo.permissions.Role;
import com.olo.authservice.domain.results.users.UserResult;

import java.util.List;

public interface GetAllUsersByRolePort {
    List<UserResult> getAllUsersByRole(Role role);
}
