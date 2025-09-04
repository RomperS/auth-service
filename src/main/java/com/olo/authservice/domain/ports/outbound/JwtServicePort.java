package com.olo.authservice.domain.ports.outbound;

public interface JwtServicePort {
    String generateRefreshToken(String username);
    long getRefreshTokenExpiration();
    Long getAccessTokenExpiration();
    Long getUserId(String token);
    String getJti(String token);
    String generateAccessToken();
    boolean validateToken(String token);
    String getTokenType(String token);
}
