package com.olo.authservice.domain.ports.inbound.permissions;

import com.olo.authservice.domain.results.permissions.PermissionResult;

import java.util.List;

public interface GetUserPermissionsPort {
    List<PermissionResult> getUserPermissions(Long userId);
}
