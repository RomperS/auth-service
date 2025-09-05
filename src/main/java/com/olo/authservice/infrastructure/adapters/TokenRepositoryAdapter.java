package com.olo.authservice.infrastructure.adapters;

import com.olo.authservice.domain.exceptions.users.UserNotFoundException;
import com.olo.authservice.domain.models.Token;
import com.olo.authservice.domain.ports.outbound.TokenRepositoryPort;
import com.olo.authservice.infrastructure.entities.TokenEntity;
import com.olo.authservice.infrastructure.entities.UserEntity;
import com.olo.authservice.infrastructure.mappers.TokenMapper;
import com.olo.authservice.infrastructure.repositories.JpaTokenRepository;
import com.olo.authservice.infrastructure.repositories.JpaUserRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class TokenRepositoryAdapter implements TokenRepositoryPort {

    private final JpaUserRepository jpaUserRepository;
    private final JpaTokenRepository jpaTokenRepository;

    @Override
    public Token save(Token token) {
        TokenEntity tokenEntity = TokenMapper.modelToEntity(token);
        UserEntity user = jpaUserRepository.findById(token.userId()).orElseThrow(() ->
                new UserNotFoundException("User not found"));

        tokenEntity.setUser(user);
        return TokenMapper.entityToModel(jpaTokenRepository.save(tokenEntity));
    }

    @Override
    public Optional<Token> findByJti(String jti) {
        return jpaTokenRepository.findByJti(jti).map(TokenMapper::entityToModel);
    }

    @Override
    public List<Token> findAllByUsername(String username) {
        UserEntity user = jpaUserRepository.findByUsername(username).orElseThrow(() ->
                new UserNotFoundException("User not found"));

        return jpaTokenRepository.findAllByUser(user)
                .stream()
                .map(TokenMapper::entityToModel)
                .toList();
    }
}
