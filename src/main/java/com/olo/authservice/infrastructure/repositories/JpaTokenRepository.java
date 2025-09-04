package com.olo.authservice.infrastructure.repositories;

import com.olo.authservice.infrastructure.entities.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTokenRepository extends JpaRepository<TokenEntity, Long> {
}
