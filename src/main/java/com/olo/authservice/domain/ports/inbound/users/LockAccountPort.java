package com.olo.authservice.domain.ports.inbound.users;

public interface LockAccountPort {
    void lockAccount(Long id);
}
