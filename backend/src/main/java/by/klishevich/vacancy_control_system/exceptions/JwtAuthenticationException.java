package by.klishevich.vacancy_control_system.exceptions;

import org.springframework.security.core.AuthenticationException;

public class JwtAuthenticationException extends AuthenticationException {
    public JwtAuthenticationException(final String message) {
        super(message);
    }
}
