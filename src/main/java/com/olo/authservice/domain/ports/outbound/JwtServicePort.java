package com.olo.authservice.domain.ports.outbound;

public interface JwtServicePort {

    String generateAccessToken();
    String generateRefreshToken(String username);

    boolean validateToken(String token);

    Long getUserId(String token);
    String getJti(String token);
    String getTokenType(String token);

    Long getAccessTokenExpiration();
    long getRefreshTokenExpiration();
}
