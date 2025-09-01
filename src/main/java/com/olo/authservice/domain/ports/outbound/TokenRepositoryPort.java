package com.olo.authservice.domain.ports.outbound;

import com.olo.authservice.domain.models.Token;

import java.util.List;
import java.util.Optional;

public interface TokenRepositoryPort {
    Token save(Token tokenModel);
    List<Token> findAllByUsername(String username);
    Optional<Token> findByJti(String jti);
}
