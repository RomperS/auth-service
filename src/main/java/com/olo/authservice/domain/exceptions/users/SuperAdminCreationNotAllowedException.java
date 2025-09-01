package com.olo.authservice.domain.exceptions.users;

import com.olo.exceptions.DomainException;

public class SuperAdminCreationNotAllowedException extends DomainException {
    public SuperAdminCreationNotAllowedException(String message) {
        super(message);
    }
    public SuperAdminCreationNotAllowedException(String message, Throwable cause) {
        super(message, cause);
    }
}
