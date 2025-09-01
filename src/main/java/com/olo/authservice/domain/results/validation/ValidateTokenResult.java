package com.olo.authservice.domain.results.validation;

public record ValidateTokenResult(
        boolean isValid,
        String type
) {
}
