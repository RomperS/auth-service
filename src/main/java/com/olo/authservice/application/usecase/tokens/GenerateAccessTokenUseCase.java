package com.olo.authservice.application.usecase.tokens;

import com.olo.authservice.domain.ports.inbound.tokens.GenerateAccessTokenPort;
import com.olo.authservice.domain.ports.outbound.JwtServicePort;
import com.olo.authservice.domain.ports.outbound.TokenRepositoryPort;
import com.olo.authservice.domain.results.tokens.AccessTokenResult;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.token.TokenService;

import java.time.Instant;

@RequiredArgsConstructor
public class GenerateAccessTokenUseCase implements GenerateAccessTokenPort {

    private final TokenRepositoryPort tokenRepositoryPort;
    private final JwtServicePort jwtServicePort;

    @Override
    public AccessTokenResult generateAccessToken(String token) {
        String accessToken = jwtServicePort.generateAccessToken();
        Instant expireAt = Instant.now().plusMillis(jwtServicePort.getAccessTokenExpiration() * 100L);

        return new AccessTokenResult(accessToken, expireAt);
    }
}
