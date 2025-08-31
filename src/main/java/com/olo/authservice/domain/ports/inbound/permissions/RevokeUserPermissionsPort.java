package com.olo.authservice.domain.ports.inbound.permissions;

import com.olo.authservice.domain.command.users.PermissionCommand;
import com.olo.authservice.domain.results.UserResult;

public interface RevokeUserPermissionsPort {
    UserResult revokeUserPermissions(PermissionCommand command, Long userId);
}
