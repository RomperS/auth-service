package com.olo.authservice.infrastructure.controllers;

import com.olo.authservice.application.service.ValidationService;
import com.olo.authservice.domain.results.validation.AuthUserResult;
import com.olo.authservice.domain.results.validation.ValidateTokenResult;
import com.olo.authservice.infrastructure.dtos.request.AuthRequestDto;
import com.olo.authservice.infrastructure.dtos.request.CreateUserRequestDto;
import com.olo.authservice.infrastructure.dtos.response.AuthResponseDto;
import com.olo.authservice.infrastructure.dtos.response.TokenPermissionsResponseDto;
import com.olo.authservice.infrastructure.dtos.response.ValidateTokenResponseDto;
import com.olo.authservice.infrastructure.mappers.ValidationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/validation")
@RequiredArgsConstructor
public class ValidationRestController {

    private final ValidationService validationService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody AuthRequestDto command) {
        AuthUserResult result = validationService.login(ValidationMapper.authRequestToAuthUserCommand(command));
        return ResponseEntity.ok().body(ValidationMapper.authResultToResponseDto(result));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody AuthRequestDto command) {
        validationService.logout(ValidationMapper.authRequestToAuthUserCommand(command));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponseDto> signup(@RequestBody CreateUserRequestDto command) {
        AuthUserResult result = validationService.signup(ValidationMapper.createUserRequestToCreateUserCommand(command));
        return ResponseEntity.ok().body(ValidationMapper.authResultToResponseDto(result));
    }

    @GetMapping("/token")
    public ResponseEntity<ValidateTokenResponseDto> validateToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        String authToken = token.substring(7);
        ValidateTokenResult result = validationService.validateToken(authToken);
        return ResponseEntity.ok().body(ValidationMapper.validateTokenResultToResponseDto(result));
    }

    @GetMapping("/permissions")
    public ResponseEntity<TokenPermissionsResponseDto> getPermissionsByToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        String authToken = token.substring(7);
        TokenPermissionsResponseDto result = ValidationMapper
                .TokenPermissionsResultToResponseDto(validationService.getPermissionsByToken(authToken));

        return ResponseEntity.ok().body(result);
    }
}
