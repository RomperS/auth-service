package com.olo.authservice.domain.results.validation;

import com.olo.authservice.domain.results.tokens.AccessTokenResult;

import java.time.Instant;

public record AuthUserResult(
        String refreshToken,
        Instant expireAt,
        AccessTokenResult accessToken
) {
}