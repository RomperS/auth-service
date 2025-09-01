package com.olo.authservice.domain.exceptions.users;

import com.olo.exceptions.DomainException;

public class UsernameTakenException extends DomainException {
    public UsernameTakenException(String message) {
        super(message);
    }
    public UsernameTakenException(String message, Throwable cause) {
        super(message, cause);
    }
}
