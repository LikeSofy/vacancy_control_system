package by.klishevich.vacancy_control_system.controllers;

import by.klishevich.vacancy_control_system.dto.auth.AuthDto;
import by.klishevich.vacancy_control_system.dto.auth.RegistrationDto;
import by.klishevich.vacancy_control_system.dto.user.UserDto;
import by.klishevich.vacancy_control_system.entity.user.UserEntity;
import by.klishevich.vacancy_control_system.service.AuthService;
import by.klishevich.vacancy_control_system.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1/auth")
public class AuthController {
    private final UserService userService;
    private final AuthService authService;

    @PostMapping(value = "/login")
    public ResponseEntity<String> login(@Valid @RequestBody AuthDto request) {
        String token = authService.createToken(userService.authenticate(request.getEmail(), request.getPassword()));
        return ResponseEntity.ok(token);
    }

    @PostMapping(value = "/registration")
    public ResponseEntity<Void> registration(@Valid @RequestBody RegistrationDto request) {
        log.info(request.toString());
        userService.create(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("!hasRole('ROLE_GUEST')")
    @GetMapping(value = "/info")
    public ResponseEntity<UserDto> info(@RequestAttribute UserEntity user) {
        return ResponseEntity.ok(userService.toDto(user));
    }
}
