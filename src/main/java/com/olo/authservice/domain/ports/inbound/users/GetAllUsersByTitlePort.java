package com.olo.authservice.domain.ports.inbound.users;

import com.olo.authservice.domain.models.permissions.Title;
import com.olo.authservice.domain.results.UserResult;

public interface GetAllUsersByTitlePort {
    UserResult getAllUsersByTitle(Title title);
}
