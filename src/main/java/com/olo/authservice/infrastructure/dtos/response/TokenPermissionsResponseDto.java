package com.olo.authservice.infrastructure.dtos.response;

import java.util.List;

public record TokenPermissionsResponseDto(
        ValidateTokenResponseDto validateToken,
        List<PermissionsResponseDto> permissions
) {
}
