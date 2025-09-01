package com.olo.authservice.domain.results.validation;

import java.time.Instant;

public record AuthUserResult(
        String refreshToken,
        Instant expireAt,
        AccessToken accessToken
) {

    public record AccessToken(
            String token,
            Instant expireAt
    ) {
    }
}