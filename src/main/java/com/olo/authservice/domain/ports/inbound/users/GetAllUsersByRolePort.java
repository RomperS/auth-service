package com.olo.authservice.domain.ports.inbound.users;

import com.olo.permissions.Role;
import com.olo.authservice.domain.results.users.UserResult;

public interface GetAllUsersByRolePort {
    UserResult getAllUsersByRole(Role role);
}
