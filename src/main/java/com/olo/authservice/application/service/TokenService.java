package com.olo.authservice.application.service;

import com.olo.authservice.application.usecase.tokens.*;
import com.olo.authservice.domain.ports.inbound.tokens.*;
import com.olo.authservice.domain.results.tokens.AccessTokenResult;
import com.olo.authservice.domain.results.tokens.TokenResult;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class TokenService implements CreateTokenPort, GenerateAccessTokenPort, GetActiveUserTokensPort, RevokeTokenPort {

    private final CreateTokenImpl createTokenImpl;
    private final GenerateAccessTokenImpl generateAccessTokenImpl;
    private final GetActiveUserTokensImpl getActiveUserTokensImpl;
    private final RevokeTokenImpl revokeTokenImpl;

    @Override
    public TokenResult createToken(String username) {
        return createTokenImpl.createToken(username);
    }

    @Override
    public AccessTokenResult generateAccessToken(String token) {
        return generateAccessTokenImpl.generateAccessToken(token);
    }

    @Override
    public List<TokenResult> getActiveUserToken(String username) {
        return getActiveUserTokensImpl.getActiveUserToken(username);
    }

    @Override
    public void revokeToken(String jti) {
        revokeTokenImpl.revokeToken(jti);
    }
}
