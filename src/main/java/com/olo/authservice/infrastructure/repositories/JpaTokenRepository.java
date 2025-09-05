package com.olo.authservice.infrastructure.repositories;

import com.olo.authservice.domain.models.Token;
import com.olo.authservice.infrastructure.entities.TokenEntity;
import com.olo.authservice.infrastructure.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpaTokenRepository extends JpaRepository<TokenEntity, Long> {
    Optional<TokenEntity> findByJti(String jti);
    List<TokenEntity> findAllByUser(UserEntity user);
}
