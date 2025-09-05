package com.olo.authservice.application.service;

import com.olo.authservice.domain.command.users.CreateUserCommand;
import com.olo.authservice.domain.command.users.UpdateUserCommand;
import com.olo.authservice.domain.ports.inbound.users.*;
import com.olo.authservice.domain.results.users.UserResult;
import com.olo.permissions.Role;
import com.olo.permissions.Title;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class UserService implements
        CreateUserPort,
        DeleteUserPort,
        GetAllUsersByRolePort,
        GetAllUsersByTitlePort,
        GetAllUsersPort,
        GetUserByIdPort,
        GetUserByUsernamePort,
        LockUserPort,
        UnlockUserPort,
        UpdateUserPort {

    private final CreateUserPort createUserPort;
    private final DeleteUserPort deleteUserPort;
    private final GetAllUsersByRolePort getAllUsersByRolePort;
    private final GetAllUsersByTitlePort getAllUsersByTitlePort;
    private final GetAllUsersPort getAllUsersPort;
    private final GetUserByIdPort getUserByIdPort;
    private final GetUserByUsernamePort getUserByUsernamePort;
    private final LockUserPort lockUserPort;
    private final UnlockUserPort unlockUserPort;
    private final UpdateUserPort updateUserPort;

    @Override
    public UserResult createUser(CreateUserCommand command) {
        return createUserPort.createUser(command);
    }

    @Override
    public void deleteUserById(Long id) {
        deleteUserPort.deleteUserById(id);
    }

    @Override
    public List<UserResult> getAllUsersByRole(Role role) {
        return getAllUsersByRolePort.getAllUsersByRole(role);
    }

    @Override
    public List<UserResult> getAllUsersByTitle(Title title) {
        return getAllUsersByTitlePort.getAllUsersByTitle(title);
    }

    @Override
    public List<UserResult> getAllUsers() {
        return getAllUsersPort.getAllUsers();
    }

    @Override
    public UserResult getUserById(Long userId) {
        return getUserByIdPort.getUserById(userId);
    }

    @Override
    public UserResult getUserByUsername(String username) {
        return getUserByUsernamePort.getUserByUsername(username);
    }

    @Override
    public void lockUser(Long userId) {
        lockUserPort.lockUser(userId);
    }

    @Override
    public void unlockUser(Long userId) {
        unlockUserPort.unlockUser(userId);
    }

    @Override
    public UserResult updateUser(UpdateUserCommand command) {
        return updateUserPort.updateUser(command);
    }
}