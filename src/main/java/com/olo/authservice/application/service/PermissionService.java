package com.olo.authservice.application.service;

import com.olo.authservice.domain.command.permissions.PermissionCommand;
import com.olo.authservice.domain.ports.inbound.permissions.AssignUserPermissionsPort;
import com.olo.authservice.domain.ports.inbound.permissions.GetUserPermissionsPort;
import com.olo.authservice.domain.ports.inbound.permissions.RevokeUserPermissionsPort;
import com.olo.authservice.domain.results.permissions.PermissionResult;
import com.olo.authservice.domain.results.users.UserResult;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class PermissionService implements GetUserPermissionsPort, RevokeUserPermissionsPort, AssignUserPermissionsPort {

    private final GetUserPermissionsPort getUserPermissionsPort;
    private final RevokeUserPermissionsPort revokeUserPermissionsPort;
    private final AssignUserPermissionsPort assignUserPermissionsPort;

    @Override
    public List<PermissionResult> getUserPermissions(Long userId) {
        return getUserPermissionsPort.getUserPermissions(userId);
    }

    @Override
    public UserResult assignUserPermissions(PermissionCommand command, Long userId) {
        return assignUserPermissionsPort.assignUserPermissions(command, userId);
    }



    @Override
    public UserResult revokeUserPermissions(PermissionCommand command, Long userId) {
        return revokeUserPermissionsPort.revokeUserPermissions(command, userId);
    }
}
