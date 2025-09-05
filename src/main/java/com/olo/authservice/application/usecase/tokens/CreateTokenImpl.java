package com.olo.authservice.application.usecase.tokens;

import com.olo.authservice.common.anotations.CustomTransactional;
import com.olo.authservice.domain.models.Token;
import com.olo.authservice.domain.ports.inbound.tokens.CreateTokenPort;
import com.olo.authservice.domain.ports.outbound.JwtServicePort;
import com.olo.authservice.domain.ports.outbound.TokenRepositoryPort;
import com.olo.authservice.domain.results.tokens.TokenResult;
import lombok.RequiredArgsConstructor;

import java.time.Instant;

@RequiredArgsConstructor
public class CreateTokenImpl implements CreateTokenPort {

    private final TokenRepositoryPort tokenRepositoryPort;
    private final JwtServicePort jwtServicePort;

    @Override
    @CustomTransactional
    public TokenResult createToken(String username) {
        String token = jwtServicePort.generateRefreshToken(username);
        Instant expireAt = Instant.now().plusMillis(jwtServicePort.getRefreshTokenExpiration());
        String jti = jwtServicePort.getJti(token);
        Long userId = jwtServicePort.getUserId(token);

        Token tokenModel = new Token(
                null,
                jti,
                userId,
                token,
                false,
                expireAt
        );

        Token savedToken = tokenRepositoryPort.save(tokenModel);
        return new TokenResult(
                savedToken.id(),
                savedToken.jti(),
                savedToken.userId(),
                savedToken.refreshToken(),
                savedToken.isRevoked(),
                savedToken.expiredAt()
        );
    }
}
