package com.olo.authservice.domain.exceptions.permissions;

import com.olo.authservice.domain.exceptions.DomainException;

public class TitleDefaultNotFoundException extends DomainException {
    public TitleDefaultNotFoundException(String message) {
        super(message);
    }
    public TitleDefaultNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
