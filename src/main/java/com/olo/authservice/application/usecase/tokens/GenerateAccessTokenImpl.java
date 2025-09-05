package com.olo.authservice.application.usecase.tokens;

import com.olo.authservice.domain.exceptions.tokens.InvalidTokenException;
import com.olo.authservice.domain.exceptions.tokens.TokenAlreadyRevokedException;
import com.olo.authservice.domain.exceptions.tokens.TokenNotFoundException;
import com.olo.authservice.domain.models.Token;
import com.olo.authservice.domain.ports.inbound.tokens.GenerateAccessTokenPort;
import com.olo.authservice.domain.ports.outbound.JwtServicePort;
import com.olo.authservice.domain.ports.outbound.TokenRepositoryPort;
import com.olo.authservice.domain.results.tokens.AccessTokenResult;
import lombok.RequiredArgsConstructor;

import java.time.Instant;

@RequiredArgsConstructor
public class GenerateAccessTokenImpl implements GenerateAccessTokenPort {

    private final TokenRepositoryPort tokenRepositoryPort;
    private final JwtServicePort jwtServicePort;

    @Override
    public AccessTokenResult generateAccessToken(String token) {
        String jti = jwtServicePort.getJti(token);

        Token refreshToken = tokenRepositoryPort.findByJti(jti).orElseThrow(() -> new TokenNotFoundException("Token not found"));

        if (refreshToken.isRevoked()){
            throw new TokenAlreadyRevokedException("Token is revoked");
        }

        if (jwtServicePort.validateToken(refreshToken.refreshToken())){
            throw new InvalidTokenException("Token is valid");
        }

        String username = jwtServicePort.getUsername(refreshToken.refreshToken());

        String accessToken = jwtServicePort.generateAccessToken(username);

        Instant expireAt = Instant.now().plusMillis(jwtServicePort.getAccessTokenExpiration());

        return new AccessTokenResult(accessToken, expireAt);
    }
}
