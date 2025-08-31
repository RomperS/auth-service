package com.olo.authservice.domain.ports.inbound.users;

import com.olo.authservice.domain.models.permissions.Role;
import com.olo.authservice.domain.results.UserResult;

public interface GetAllUsersByRolePort {
    UserResult getAllUsersByRole(Role role);
}
