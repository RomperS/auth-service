package com.olo.authservice.domain.models.permissions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Title {
    PRINCIPAL(Role.SUPER_ADMIN),
    SECRETARY(Role.ADMIN),
    LIBRARIAN(Role.ADMIN),

    TUTOR(Role.TEACHER),
    HOMEROOM_TEACHER(Role.TEACHER),
    DEPARTMENT_HEAD(Role.TEACHER),

    PUPIL(Role.STUDENT),
    GROUP_REPRESENTATIVE(Role.STUDENT),
    COMPTROLLER(Role.STUDENT),
    STUDENT_REPRESENTATIVE(Role.STUDENT),

    GUARDIAN(Role.PARENT),
    PARENTS_BOARD(Role.PARENT),

    DIRECTORS_BOARD(Role.AUXILIARY_ADMIN),

    SCHOOL_COUNSELOR(Role.COUNSELOR),
    PSYCHOLOGIST(Role.COUNSELOR),
    SOCIAL_WORKER(Role.COUNSELOR);

    private final Role role;
}