package com.olo.authservice.application.usecase.permissions;

import com.olo.authservice.domain.exceptions.users.UserNotFoundException;
import com.olo.authservice.domain.models.User;
import com.olo.authservice.domain.ports.inbound.permissions.GetUserPermissionsPort;
import com.olo.authservice.domain.ports.inbound.users.GetAllUsersByRolePort;
import com.olo.authservice.domain.ports.outbound.UserRepositoryPort;
import com.olo.authservice.domain.results.permissions.PermissionResult;
import com.olo.permissions.Role;
import com.olo.permissions.Title;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class GetUserPermissionsImpl implements GetUserPermissionsPort {

    private final UserRepositoryPort userRepositoryPort;


    @Override
    public List<PermissionResult> getUserPermissions(Long userId) {
        User user = userRepositoryPort.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Map<Role, List<Title>> titlesByRole = user.titles().stream()
                .collect(Collectors.groupingBy(Title::getRole));

        List<PermissionResult> permissions = new ArrayList<>();
        for (Role role : user.roles()) {
            List<Title> matchingTitles = titlesByRole.getOrDefault(role, Collections.emptyList());
            for (Title title : matchingTitles) {
                permissions.add(new PermissionResult(title, role));
            }
        }
        return permissions;
    }
}
