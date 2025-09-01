package com.olo.authservice.domain.ports.inbound.tokens;

import com.olo.authservice.domain.results.tokens.AccessTokenResult;

public interface GenerateAccessTokenPort {
    AccessTokenResult generateAccessToken(String token);
}
