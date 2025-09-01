package com.olo.authservice.domain.ports.inbound.tokens;

import com.olo.authservice.domain.results.tokens.TokenResult;

public interface CreateTokenPort {
    TokenResult createToken(String username);
}
