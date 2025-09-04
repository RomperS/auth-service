package com.olo.authservice.domain.ports.outbound;

import com.olo.authservice.domain.models.User;
import com.olo.permissions.Role;
import com.olo.permissions.Title;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryPort {
    boolean existByUsername(String username);
    boolean existByEmail(String email);
    User save(User user);
    void delete(Long id);
    List<User> findUsersByRole(Role role);
    List<User> findUsersByTitle(Title title);
    List<User> findAll();
    Optional<User> findByUsername(String username);
    Optional<User> findById(Long id);
}
