package com.olo.authservice.domain.ports.inbound.permissions;

import com.olo.authservice.domain.command.users.PermissionCommand;
import com.olo.authservice.domain.results.UserResult;

public interface AssignUserPermissionsPort {
    UserResult assignUserPermissions(PermissionCommand command, Long userId);
}
