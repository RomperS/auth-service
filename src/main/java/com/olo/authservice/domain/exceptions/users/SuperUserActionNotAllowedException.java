package com.olo.authservice.domain.exceptions.users;

import com.olo.exceptions.DomainException;

public class SuperUserActionNotAllowedException extends DomainException {
    public SuperUserActionNotAllowedException(String message) {
        super(message);
    }
    public SuperUserActionNotAllowedException(String message, Throwable cause) {
        super(message, cause);
    }
}
