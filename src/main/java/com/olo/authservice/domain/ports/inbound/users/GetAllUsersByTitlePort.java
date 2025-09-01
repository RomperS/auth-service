package com.olo.authservice.domain.ports.inbound.users;

import com.olo.permissions.Title;
import com.olo.authservice.domain.results.users.UserResult;

public interface GetAllUsersByTitlePort {
    UserResult getAllUsersByTitle(Title title);
}
