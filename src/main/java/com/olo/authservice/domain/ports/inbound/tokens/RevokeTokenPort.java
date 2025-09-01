package com.olo.authservice.domain.ports.inbound.tokens;

public interface RevokeTokenPort {
    void revokeToken(String jti);
}
