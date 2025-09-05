package com.olo.authservice.application.service;

import com.olo.authservice.domain.command.users.CreateUserCommand;
import com.olo.authservice.domain.command.users.UpdateUserCommand;
import com.olo.authservice.domain.ports.inbound.users.*;
import com.olo.authservice.application.usecase.users.*;
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

    private final CreateUserImpl createUserImpl;
    private final DeleteUserImpl deleteUserImpl;
    private final GetAllUsersByRoleImpl getAllUsersByRoleImpl;
    private final GetAllUsersByTitleImpl getAllUsersByTitleImpl;
    private final GetAllUsersImpl getAllUsersImpl;
    private final GetUserByIdImpl getUserByIdImpl;
    private final GetUserByUsernameImpl getUserByUsernameImpl;
    private final LockUserImpl lockUserImpl;
    private final UnlockUserImpl unlockUserImpl;
    private final UpdateUserImpl updateUserImpl;

    @Override
    public UserResult createUser(CreateUserCommand command) {
        return createUserImpl.createUser(command);
    }

    @Override
    public void deleteUserById(Long id) {
        deleteUserImpl.deleteUserById(id);
    }

    @Override
    public List<UserResult> getAllUsersByRole(Role role) {
        return getAllUsersByRoleImpl.getAllUsersByRole(role);
    }

    @Override
    public List<UserResult> getAllUsersByTitle(Title title) {
        return getAllUsersByTitleImpl.getAllUsersByTitle(title);
    }

    @Override
    public List<UserResult> getAllUsers() {
        return getAllUsersImpl.getAllUsers();
    }

    @Override
    public UserResult getUserById(Long userId) {
        return getUserByIdImpl.getUserById(userId);
    }

    @Override
    public UserResult getUserByUsername(String username) {
        return getUserByUsernameImpl.getUserByUsername(username);
    }

    @Override
    public void lockUser(Long userId) {
        lockUserImpl.lockUser(userId);
    }

    @Override
    public void unlockUser(Long userId) {
        unlockUserImpl.unlockUser(userId);
    }

    @Override
    public UserResult updateUser(UpdateUserCommand command) {
        return updateUserImpl.updateUser(command);
    }
}