package com.olo.authservice.application.service;

import com.olo.authservice.application.usecase.permissions.*;
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

    private final GetUserPermissionsImpl getUserPermissionsImpl;
    private final RevokeUserPermissionsImpl revokeUserPermissionsImpl;
    private final AssignUserPermissionsImpl assignUserPermissionsImpl;

    @Override
    public List<PermissionResult> getUserPermissions(Long userId) {
        return getUserPermissionsImpl.getUserPermissions(userId);
    }

    @Override
    public UserResult assignUserPermissions(PermissionCommand command, Long userId) {
        return assignUserPermissionsImpl.assignUserPermissions(command, userId);
    }

    @Override
    public UserResult revokeUserPermissions(PermissionCommand command, Long userId) {
        return revokeUserPermissionsImpl.revokeUserPermissions(command, userId);
    }
}
