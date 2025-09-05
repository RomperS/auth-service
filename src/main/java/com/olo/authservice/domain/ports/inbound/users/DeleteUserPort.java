package com.olo.authservice.domain.ports.inbound.users;

public interface DeleteUserPort {
    void deleteUserById(Long id);
}
