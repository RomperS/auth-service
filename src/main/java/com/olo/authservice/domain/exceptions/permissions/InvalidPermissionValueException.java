package com.olo.authservice.domain.exceptions.permissions;

import com.olo.authservice.domain.exceptions.DomainException;

public class InvalidPermissionValueException extends DomainException {
    public InvalidPermissionValueException(String message) {
        super(message);
    }
    public InvalidPermissionValueException(String message, Throwable cause) {
        super(message, cause);
    }
}
