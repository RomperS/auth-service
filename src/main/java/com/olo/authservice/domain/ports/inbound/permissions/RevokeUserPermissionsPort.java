package com.olo.authservice.domain.ports.inbound.permissions;

import com.olo.authservice.domain.command.permissions.PermissionCommand;
import com.olo.authservice.domain.results.users.UserResult;

public interface RevokeUserPermissionsPort {
    UserResult revokeUserPermissions(PermissionCommand command, Long userId);
}
