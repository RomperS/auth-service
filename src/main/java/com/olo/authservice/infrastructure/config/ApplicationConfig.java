package com.olo.authservice.infrastructure.config;

import com.olo.authservice.application.service.PermissionService;
import com.olo.authservice.application.service.TokenService;
import com.olo.authservice.application.service.UserService;
import com.olo.authservice.application.service.ValidationService;
import com.olo.authservice.application.usecase.permissions.AssignUserPermissionsImpl;
import com.olo.authservice.application.usecase.permissions.GetUserPermissionsImpl;
import com.olo.authservice.application.usecase.permissions.RevokeUserPermissionsImpl;
import com.olo.authservice.application.usecase.users.*;
import com.olo.authservice.application.usecase.tokens.*;
import com.olo.authservice.application.usecase.validation.*;
import com.olo.authservice.domain.ports.outbound.JwtServicePort;
import com.olo.authservice.domain.ports.outbound.PasswordEncoderPort;
import com.olo.authservice.domain.ports.outbound.TokenRepositoryPort;
import com.olo.authservice.domain.ports.outbound.UserRepositoryPort;
import com.olo.authservice.infrastructure.adapters.TokenRepositoryAdapter;
import com.olo.authservice.infrastructure.adapters.UserRepositoryAdapter;
import com.olo.authservice.infrastructure.repositories.JpaTokenRepository;
import com.olo.authservice.infrastructure.security.JwtServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class ApplicationConfig {

    @Bean
    public UserRepositoryPort userRepositoryPort(UserRepositoryAdapter userRepositoryAdapter) {
        return userRepositoryAdapter;
    }

    @Bean
    public TokenRepositoryPort tokenRepositoryPort(TokenRepositoryAdapter tokenRepositoryAdapter) {
        return tokenRepositoryAdapter;
    }

    @Bean
    public UserService userService(UserRepositoryPort userRepositoryPort, PasswordEncoderPort passwordEncoderPort) {
        return new UserService(
                new CreateUserImpl(userRepositoryPort, passwordEncoderPort),
                new DeleteUserImpl(userRepositoryPort),
                new GetAllUsersByRoleImpl(userRepositoryPort),
                new GetAllUsersByTitleImpl(userRepositoryPort),
                new GetAllUsersImpl(userRepositoryPort),
                new GetUserByIdImpl(userRepositoryPort),
                new GetUserByUsernameImpl(userRepositoryPort),
                new LockUserImpl(userRepositoryPort),
                new UnlockUserImpl(userRepositoryPort),
                new UpdateUserImpl(userRepositoryPort, passwordEncoderPort)
        );
    }

    @Bean
    public TokenService tokenService(TokenRepositoryPort tokenRepositoryPort, JwtServicePort jwtServicePort) {
        return new TokenService(
                new CreateTokenImpl(tokenRepositoryPort, jwtServicePort),
                new GenerateAccessTokenImpl(tokenRepositoryPort, jwtServicePort),
                new GetActiveUserTokensImpl(tokenRepositoryPort),
                new RevokeTokenImpl(tokenRepositoryPort)
        );
    }

    @Bean
    public PermissionService permissionService(UserRepositoryPort userRepositoryPort) {
        return new PermissionService(
                new GetUserPermissionsImpl(userRepositoryPort),
                new RevokeUserPermissionsImpl(userRepositoryPort),
                new AssignUserPermissionsImpl(userRepositoryPort)
        );
    }

    @Bean
    public ValidationService validationService(PasswordEncoderPort passwordEncoderPort,
                                               UserRepositoryPort userRepositoryPort,
                                               TokenService tokenService,
                                               UserService userService,
                                               JwtServicePort jwtServicePort,
                                               PermissionService permissionService) {
        return new ValidationService(
                new LoginImpl(passwordEncoderPort, userRepositoryPort, tokenService),
                new LogoutImpl(tokenService, userRepositoryPort, passwordEncoderPort),
                new SignupImpl(userService, tokenService),
                new ValidateTokenImpl(jwtServicePort),
                new GetPermissionsByTokenImpl(jwtServicePort, permissionService)
        );
    }

    @Bean
    public JwtServicePort jwtServicePort(JpaTokenRepository jpaTokenRepository, UserDetailsService userDetailsService) {
        return new JwtServiceImpl(jpaTokenRepository, userDetailsService);
    }
}
