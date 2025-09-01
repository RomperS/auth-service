package com.olo.authservice.domain.ports.inbound.validation;

import com.olo.authservice.domain.results.validation.ValidateTokenResult;

public interface ValidateTokenPort {
    ValidateTokenResult validateToken(String token);
}
