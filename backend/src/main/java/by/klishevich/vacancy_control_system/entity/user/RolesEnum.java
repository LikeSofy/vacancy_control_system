package by.klishevich.vacancy_control_system.entity.user;

import org.springframework.security.core.GrantedAuthority;

public enum RolesEnum implements GrantedAuthority {
    ROLE_USER,
    ROLE_GUEST,
    ROLE_DIRECTOR,
    ROLE_ADMINISTRATOR,
    ROLE_INTERVIEWER;

    @Override
    public String getAuthority() {
        return toString();
    }
}
