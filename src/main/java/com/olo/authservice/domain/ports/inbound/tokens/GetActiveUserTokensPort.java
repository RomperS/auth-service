package com.olo.authservice.domain.ports.inbound.tokens;

import com.olo.authservice.domain.results.tokens.TokenResult;

import java.util.List;

public interface GetActiveUserTokensPort {
    List<TokenResult> getActiveUserToken(String username);
}
