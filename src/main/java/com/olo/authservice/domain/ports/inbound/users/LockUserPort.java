package com.olo.authservice.domain.ports.inbound.users;

public interface LockUserPort {
    void lockUser(Long userId);
}
