package by.klishevich.vacancy_control_system.repository;

import by.klishevich.vacancy_control_system.entity.user.UserEntity;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
}
