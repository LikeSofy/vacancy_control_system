package by.klishevich.vacancy_control_system.controllers;

import by.klishevich.vacancy_control_system.criteria.VacancyCriteria;
import by.klishevich.vacancy_control_system.dto.vacancy.VacancyCreateEditRequest;
import by.klishevich.vacancy_control_system.dto.vacancy.VacancyDto;
import by.klishevich.vacancy_control_system.entity.PageDto;
import by.klishevich.vacancy_control_system.service.VacancyService;
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
@RequestMapping(value = "/api/v1/vacancies")
public class VacancyController {
    private final VacancyService service;

    @GetMapping(params = {"sortBy", "sortDirection", "pageNumber", "pageSize"})
    public ResponseEntity<PageDto<VacancyDto>> findAll(
            VacancyCriteria criteria
    ) {
        return new ResponseEntity<>(service.findAll(criteria), HttpStatus.OK);
    }

    @GetMapping(params = {"ids"})
    public ResponseEntity<List<VacancyDto>> findAllByIds(
            @RequestParam(value = "ids") List<Long> ids
    ) {
        return new ResponseEntity<>(service.findAllByIds(ids), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<VacancyDto> findById(@PathVariable Long id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR', 'ROLE_DIRECTOR')")
    @PostMapping()
    public ResponseEntity<VacancyDto> create(
            @Valid @RequestBody VacancyCreateEditRequest request
    ) {
        return new ResponseEntity<>(service.create(request), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR', 'ROLE_DIRECTOR')")
    @PutMapping("/{id}")
    public ResponseEntity<VacancyDto> update(
            @PathVariable Long id,
            @Valid @RequestBody VacancyCreateEditRequest request
    ) {
        return new ResponseEntity<>(service.update(id, request), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR', 'ROLE_DIRECTOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR', 'ROLE_DIRECTOR')")
    @DeleteMapping()
    public ResponseEntity<Void> delete(
            @RequestParam(value = "ids") List<Long> ids
    ) {
        service.delete(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
