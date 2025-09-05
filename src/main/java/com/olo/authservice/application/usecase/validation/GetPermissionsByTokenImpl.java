package com.olo.authservice.application.usecase.validation;

import com.olo.authservice.application.service.PermissionService;
import com.olo.authservice.domain.ports.inbound.validation.GetPermissionsByTokenPort;
import com.olo.authservice.domain.ports.outbound.JwtServicePort;
import com.olo.authservice.domain.results.permissions.PermissionResult;
import com.olo.authservice.domain.results.validation.TokenPermissionsResult;
import com.olo.authservice.domain.results.validation.ValidateTokenResult;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetPermissionsByTokenImpl implements GetPermissionsByTokenPort {

    private final JwtServicePort jwtServicePort;
    private final PermissionService permissionService;

    @Override
    public TokenPermissionsResult getPermissionsByToken(String token) {
        boolean isValid = jwtServicePort.validateToken(token);
        String type = jwtServicePort.getTokenType(token);

        ValidateTokenResult validateResult = new ValidateTokenResult(isValid, type);

        Long userId = jwtServicePort.getUserId(token);

        List<PermissionResult> permissions = permissionService.getUserPermissions(userId);

        return new TokenPermissionsResult(validateResult, permissions);
    }
}
