package com.olo.authservice.domain.ports.outbound;

import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public interface JwtServicePort {
    String generateRefreshToken(String username);
    long getRefreshTokenExpiration();
    Long getAccessTokenExpiration();
    Long getUserId(String token);
    String getJti(String token);
    String generateAccessToken();
}
