package by.klishevich.vacancy_control_system.service;

import by.klishevich.vacancy_control_system.builder.UserBuilder;
import by.klishevich.vacancy_control_system.dto.auth.RegistrationDto;
import by.klishevich.vacancy_control_system.dto.user.UpdateUserRequest;
import by.klishevich.vacancy_control_system.dto.user.UserDto;
import by.klishevich.vacancy_control_system.entity.user.RolesEnum;
import by.klishevich.vacancy_control_system.entity.user.UserEntity;
import by.klishevich.vacancy_control_system.exceptions.AuthException;
import by.klishevich.vacancy_control_system.exceptions.BadRequestException;
import by.klishevich.vacancy_control_system.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Slf4j
@Service

public class UserService extends BaseService<UserDto, UserEntity, UpdateUserRequest, RegistrationDto> implements UserDetailsService {
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserBuilder userBuilder;

    @Value("${superuser.name}")
    private String superuserName;

    @Value("${superuser.surname}")
    private String superuserSurname;

    @Value("${superuser.email}")
    private String superuserEmail;

    @Value("${superuser.password}")
    private String superuserPassword;

    @PostConstruct
    private void CreateSuperUser() {
        Optional<UserEntity> optionalAdmin = userRepository.findByEmail(superuserEmail);

        if (optionalAdmin.isPresent()) {
            return;
        }

        UserEntity admin = userBuilder
                .setEmail(superuserEmail)
                .setName(superuserName)
                .setSurname(superuserSurname)
                .setRole(RolesEnum.ROLE_ADMINISTRATOR)
                .setPassword(superuserPassword)
                .create();

        userRepository.save(admin);
    }

    public UserService(UserRepository repository, BCryptPasswordEncoder passwordEncoder, UserBuilder userBuilder) {
        super(repository);
        this.passwordEncoder = passwordEncoder;
        this.userRepository = repository;
        this.userBuilder = userBuilder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public UserEntity authenticate(String email, String password) {
        final UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AuthException("Invalid login or password"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new AuthException("Invalid login or password");
        }

        return user;
    }

    public UserDto toDto(UserEntity user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setSurname(user.getSurname());
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRole());

        return userDto;
    }

    @Override
    public UserEntity toEntity(RegistrationDto registrationDto) {
        if (userRepository.findByEmail(registrationDto.getEmail()).isPresent()) {
            throw new BadRequestException("User with this email already registered");
        }

        return userBuilder
                .setName(registrationDto.getName())
                .setSurname(registrationDto.getSurname())
                .setEmail(registrationDto.getEmail())
                .setRole(RolesEnum.ROLE_USER)
                .setPassword(registrationDto.getPassword())
                .create();
    }

    @Override
    public UserEntity update(UserEntity entity, UpdateUserRequest request) {
        log.info(request.toString());
        entity.setName(request.getName());
        entity.setSurname(request.getSurname());
        entity.setEmail(request.getEmail());
        entity.setRole(request.getRole());
        request.getPassword().ifPresent(password -> {
            entity.setPassword(passwordEncoder.encode(password));
        });

        return entity;
    }
}
