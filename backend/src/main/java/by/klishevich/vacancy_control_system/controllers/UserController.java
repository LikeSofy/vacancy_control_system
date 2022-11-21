package by.klishevich.vacancy_control_system.controllers;

import by.klishevich.vacancy_control_system.criteria.UserCriteria;
import by.klishevich.vacancy_control_system.dto.user.UpdateUserRequest;
import by.klishevich.vacancy_control_system.dto.user.UserDto;
import by.klishevich.vacancy_control_system.entity.PageDto;
import by.klishevich.vacancy_control_system.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping(value = "/api/v1/users")
public class UserController {
    private final UserService userService;

    @GetMapping(params = {"sortBy", "sortDirection", "pageNumber", "pageSize"})
    public ResponseEntity<PageDto<UserDto>> findAll(
            UserCriteria criteria
    ) {
        log.info(criteria.toString());
        return new ResponseEntity<>(userService.findAll(criteria), HttpStatus.OK);
    }

    @GetMapping(params = {"ids"})
    public ResponseEntity<List<UserDto>> findAllByIds(
            @RequestParam(value = "ids") List<Long> ids
    ) {
        return new ResponseEntity<>(userService.findAllByIds(ids), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR')")
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateUserRequest request
    ) {
        return new ResponseEntity<>(userService.update(id, request), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR')")
    @DeleteMapping()
    public ResponseEntity<Void> delete(
            @RequestParam(value = "ids") List<Long> ids
    ) {
        userService.delete(ids);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
