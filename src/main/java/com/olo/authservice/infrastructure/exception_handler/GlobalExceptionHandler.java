package com.olo.authservice.infrastructure.exception_handler;

import com.olo.authservice.domain.exceptions.tokens.InvalidTokenException;
import com.olo.authservice.domain.exceptions.tokens.MissingTokenException;
import com.olo.authservice.domain.exceptions.tokens.TokenAlreadyRevokedException;
import com.olo.authservice.domain.exceptions.users.EmailAlreadyExistsException;
import com.olo.authservice.domain.exceptions.users.SuperAdminCreationNotAllowedException;
import com.olo.authservice.domain.exceptions.users.UserNotFoundException;
import com.olo.authservice.domain.exceptions.users.UsernameTakenException;
import com.olo.authservice.domain.exceptions.validation.InvalidCredentialsException;
import com.olo.authservice.utils.framework.HttpResponseUtil;
import com.olo.exceptions.DomainException;
import com.olo.exceptions.permissions.InvalidPermissionValueException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final HttpResponseUtil httpResponseUtil;

    public GlobalExceptionHandler(HttpResponseUtil httpResponseUtil) {
        this.httpResponseUtil = httpResponseUtil;
    }

    @ExceptionHandler(DomainException.class)
    public void handleDomainException(DomainException ex, HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpStatus status;

        if (ex instanceof InvalidTokenException || ex instanceof TokenAlreadyRevokedException || ex instanceof InvalidCredentialsException || ex instanceof SuperAdminCreationNotAllowedException) {
            status = HttpStatus.UNAUTHORIZED;
        } else if (ex instanceof MissingTokenException || ex instanceof InvalidPermissionValueException) {
            status = HttpStatus.BAD_REQUEST;
        } else if (ex instanceof UserNotFoundException) {
            status = HttpStatus.NOT_FOUND;
        } else if (ex instanceof UsernameTakenException || ex instanceof EmailAlreadyExistsException) {
            status = HttpStatus.CONFLICT;
        } else {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        httpResponseUtil.writeErrorResponse(response, status, ex.getClass().getSimpleName(), ex.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(Exception.class)
    public void handleOtherExceptions(Exception ex, HttpServletRequest request, HttpServletResponse response) throws IOException {
        httpResponseUtil.writeErrorResponse(response, HttpStatus.INTERNAL_SERVER_ERROR, "Internal Error", ex.getMessage(), request.getRequestURI());
    }
}
