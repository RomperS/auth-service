package com.olo.authservice.domain.results.tokens;

import java.time.Instant;

public record AccessTokenResult(
        String token,
        Instant expireAt
) {
}
