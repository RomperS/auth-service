package com.olo.authservice.domain.exceptions.tokens;

import com.olo.exceptions.DomainException;

public class TokenAlreadyRevokedException extends DomainException {
    public TokenAlreadyRevokedException(String message) {
        super(message);
    }
    public TokenAlreadyRevokedException(String message, Throwable cause) {
        super(message, cause);
    }
}
