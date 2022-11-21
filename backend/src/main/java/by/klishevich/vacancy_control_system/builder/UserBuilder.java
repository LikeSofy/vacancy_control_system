package by.klishevich.vacancy_control_system.builder;

import by.klishevich.vacancy_control_system.entity.user.RolesEnum;
import by.klishevich.vacancy_control_system.entity.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Builder pattern
 * <a href="https://refactoring.guru/ru/design-patterns/builder/java/example#lang-features">more info about pattern</a>
 */
@Component
@RequiredArgsConstructor
public class UserBuilder {
    private final BCryptPasswordEncoder passwordEncoder;

    private String name;
    private String surname;
    private String email;
    private String password;
    private RolesEnum role = RolesEnum.ROLE_USER;

    public UserBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder setRole(RolesEnum rolesEnum) {
        this.role = rolesEnum;
        return this;
    }

    public UserEntity create() {
        UserEntity user = new UserEntity();
        user.setEmail(email);
        user.setName(name);
        user.setSurname(surname);
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(password));

        return user;
    }
}
