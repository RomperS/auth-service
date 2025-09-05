package com.olo.authservice.domain.ports.inbound.validation;

import com.olo.authservice.domain.results.validation.TokenPermissionsResult;

public interface GetPermissionsByTokenPort {
    TokenPermissionsResult getPermissionsByToken(String token);
}
