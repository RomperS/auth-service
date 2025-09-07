package com.olo.authservice.infrastructure.mappers;

import com.olo.authservice.domain.command.users.CreateUserCommand;
import com.olo.authservice.domain.command.validation.AuthUserCommand;
import com.olo.authservice.domain.results.permissions.PermissionResult;
import com.olo.authservice.domain.results.validation.AuthUserResult;
import com.olo.authservice.domain.results.validation.TokenPermissionsResult;
import com.olo.authservice.domain.results.validation.ValidateTokenResult;
import com.olo.authservice.infrastructure.dtos.request.AuthRequestDto;
import com.olo.authservice.infrastructure.dtos.request.CreateUserRequestDto;
import com.olo.authservice.infrastructure.dtos.response.*;

public class ValidationMapper {

    public static AuthResponseDto authResultToResponseDto(AuthUserResult authUserResult){
        if(authUserResult == null){
            return null;
        }

        return new AuthResponseDto(
                authUserResult.refreshToken(),
                authUserResult.expireAt(),
                new AccessTokenResponseDto(
                        authUserResult.accessToken().token(),
                        authUserResult.accessToken().expireAt()
                )
        );
    }

    public static AuthUserCommand authRequestToAuthUserCommand(AuthRequestDto authRequestDto){
        if (authRequestDto == null){
            return null;
        }

        return new AuthUserCommand(
                authRequestDto.username(),
                authRequestDto.password()
        );
    }

    public static CreateUserCommand createUserRequestToCreateUserCommand(CreateUserRequestDto createUserRequestDto){
        if (createUserRequestDto == null){
            return null;
        }

        return new CreateUserCommand(
                createUserRequestDto.username(),
                createUserRequestDto.email(),
                createUserRequestDto.password(),
                createUserRequestDto.role(),
                createUserRequestDto.title()
        );
    }

    public static ValidateTokenResponseDto validateTokenResultToResponseDto(ValidateTokenResult validateTokenResult){
        if (validateTokenResult == null){
            return null;
        }

        return new ValidateTokenResponseDto(
                validateTokenResult.isValid(),
                validateTokenResult.type()
        );
    }

    public static PermissionsResponseDto permissionsResultToResponseDto(PermissionResult permissionResult){
        if (permissionResult == null){
            return null;
        }

        return new PermissionsResponseDto(
                permissionResult.title(),
                permissionResult.role()
        );
    }

    public static TokenPermissionsResponseDto TokenPermissionsResultToResponseDto(TokenPermissionsResult tokenPermissionsResult){
        if (tokenPermissionsResult == null){
            return null;
        }

        return new TokenPermissionsResponseDto(
                validateTokenResultToResponseDto(tokenPermissionsResult.validateToken()),
                tokenPermissionsResult
                        .permissions()
                        .stream()
                        .map(ValidationMapper::permissionsResultToResponseDto)
                        .toList()
        );
    }
}
