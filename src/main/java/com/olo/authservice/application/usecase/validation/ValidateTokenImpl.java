package com.olo.authservice.application.usecase.validation;

import com.olo.authservice.common.anotations.CustomTransactional;
import com.olo.authservice.domain.ports.inbound.validation.ValidateTokenPort;
import com.olo.authservice.domain.ports.outbound.JwtServicePort;
import com.olo.authservice.domain.results.validation.ValidateTokenResult;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ValidateTokenImpl implements ValidateTokenPort {

    private final JwtServicePort jwtServicePort;

    @Override
    @CustomTransactional
    public ValidateTokenResult validateToken(String token) {
        boolean isValid = jwtServicePort.validateToken(token);

        String type = "";
        if (isValid) {
            type =jwtServicePort.getTokenType(token);
        }

        return new ValidateTokenResult(isValid, type);
    }
}
