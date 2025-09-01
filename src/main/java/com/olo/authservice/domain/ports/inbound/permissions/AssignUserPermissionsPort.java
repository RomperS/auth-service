package com.olo.authservice.domain.ports.inbound.permissions;

import com.olo.authservice.domain.command.permissions.PermissionCommand;
import com.olo.authservice.domain.results.users.UserResult;

public interface AssignUserPermissionsPort {
    UserResult assignUserPermissions(PermissionCommand command, Long userId);
}
