package com.olo.authservice.domain.results.tokens;

import java.time.Instant;

public record TokenResult(
        Long id,
        String jti,
        Long userId,
        String refreshToken,
        boolean isRevoked,
        Instant expiredAt
) {
}
