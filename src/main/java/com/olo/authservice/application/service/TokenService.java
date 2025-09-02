package com.olo.authservice.application.service;

import com.olo.authservice.domain.ports.inbound.tokens.CreateTokenPort;
import com.olo.authservice.domain.ports.inbound.tokens.GenerateAccessTokenPort;
import com.olo.authservice.domain.ports.inbound.tokens.GetActiveUserTokensPort;
import com.olo.authservice.domain.ports.inbound.tokens.RevokeTokenPort;
import com.olo.authservice.domain.results.tokens.AccessTokenResult;
import com.olo.authservice.domain.results.tokens.TokenResult;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class TokenService implements CreateTokenPort, GenerateAccessTokenPort, GetActiveUserTokensPort, RevokeTokenPort {

    private final CreateTokenPort createTokenPort;
    private final GenerateAccessTokenPort generateAccessTokenPort;
    private final GetActiveUserTokensPort getActiveUserTokensPort;
    private final RevokeTokenPort revokeTokenPort;

    @Override
    public TokenResult createToken(String username) {
        return createTokenPort.createToken(username);
    }

    @Override
    public AccessTokenResult generateAccessToken(String token) {
        return generateAccessTokenPort.generateAccessToken(token);
    }

    @Override
    public List<TokenResult> getActiveUserToken(String username) {
        return getActiveUserTokensPort.getActiveUserToken(username);
    }

    @Override
    public void revokeToken(String jti) {
        revokeTokenPort.revokeToken(jti);
    }
}
