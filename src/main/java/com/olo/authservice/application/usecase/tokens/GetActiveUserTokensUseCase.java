package com.olo.authservice.application.usecase.tokens;

import com.olo.authservice.domain.models.Token;
import com.olo.authservice.domain.ports.inbound.tokens.GetActiveUserTokensPort;
import com.olo.authservice.domain.ports.outbound.TokenRepositoryPort;
import com.olo.authservice.domain.results.tokens.TokenResult;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.List;

@RequiredArgsConstructor
public class GetActiveUserTokensUseCase implements GetActiveUserTokensPort {

    private final TokenRepositoryPort tokenRepositoryPort;

    @Override
    public List<TokenResult> getActiveUserToken(String username) {
        List<Token> tokens = tokenRepositoryPort.findAllByUsername(username);

        List<Token> filteredToken = tokens.stream()
                .filter(token -> !token.isRevoked() && (Instant.now().isAfter(token.expiredAt())))
                .toList();

        return filteredToken.stream()
                .map(token -> new TokenResult(
                        token.id(),
                        token.jti(),
                        token.userId(),
                        token.refreshToken(),
                        token.isRevoked(),
                        token.expiredAt()
                ))
                .toList();
    }
}
