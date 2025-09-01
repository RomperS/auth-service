package com.olo.authservice.domain.ports.inbound.users;

import com.olo.permissions.Title;
import com.olo.authservice.domain.results.users.UserResult;

import java.util.List;

public interface GetAllUsersByTitlePort {
    List<UserResult> getAllUsersByTitle(Title title);
}
