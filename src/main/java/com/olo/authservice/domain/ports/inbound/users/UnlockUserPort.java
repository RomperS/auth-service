package com.olo.authservice.domain.ports.inbound.users;

public interface UnlockUserPort {
    void unlockUser(Long userId);
}
