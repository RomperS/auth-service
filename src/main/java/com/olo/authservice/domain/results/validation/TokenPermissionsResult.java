package com.olo.authservice.domain.results.validation;

import com.olo.authservice.domain.results.permissions.PermissionResult;

import java.util.List;

public record TokenPermissionsResult(
        ValidateTokenResult validateToken,
        List<PermissionResult> permissions
) {
}