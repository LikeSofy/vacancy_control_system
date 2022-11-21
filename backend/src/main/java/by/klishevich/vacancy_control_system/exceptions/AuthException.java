package by.klishevich.vacancy_control_system.exceptions;

import org.springframework.security.core.AuthenticationException;

public class AuthException extends AuthenticationException {
    public AuthException(String msg) {
        super(msg);
    }
}
