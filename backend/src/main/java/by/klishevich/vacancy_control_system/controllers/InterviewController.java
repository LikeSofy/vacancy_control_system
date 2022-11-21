package by.klishevich.vacancy_control_system.controllers;

import by.klishevich.vacancy_control_system.criteria.InterviewCriteria;
import by.klishevich.vacancy_control_system.dto.interview.InterviewCreateEditRequest;
import by.klishevich.vacancy_control_system.dto.interview.InterviewDto;
import by.klishevich.vacancy_control_system.entity.PageDto;
import by.klishevich.vacancy_control_system.entity.user.UserEntity;
import by.klishevich.vacancy_control_system.service.InterviewService;
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
@RequestMapping(value = "/api/v1/interviews")
public class InterviewController {
    private final InterviewService service;

    @GetMapping(params = {"sortBy", "sortDirection", "pageNumber", "pageSize"})
    public ResponseEntity<PageDto<InterviewDto>> findAll(
            InterviewCriteria interviewCriteria
    ) {
        return new ResponseEntity<>(service.findAll(interviewCriteria), HttpStatus.OK);
    }

    @GetMapping(params = {"ids"})
    public ResponseEntity<List<InterviewDto>> findAllByIds(
            @RequestParam(value = "ids") List<Long> ids
    ) {
        return new ResponseEntity<>(service.findAllByIds(ids), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<InterviewDto> findById(@PathVariable Long id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR', 'ROLE_INTERVIEWER')")
    @PostMapping()
    public ResponseEntity<InterviewDto> create(
            @Valid @RequestBody InterviewCreateEditRequest request
    ) {
        return new ResponseEntity<>(service.create(request), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR', 'ROLE_INTERVIEWER')")
    @PutMapping("/{id}")
    public ResponseEntity<InterviewDto> update(
            @PathVariable Long id,
            @Valid @RequestBody InterviewCreateEditRequest request
    ) {
        return new ResponseEntity<>(service.update(id, request), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @GetMapping(value = "/{id}/subscribe")
    public ResponseEntity<InterviewDto> subscribe(
            @PathVariable Long id,
            @RequestAttribute UserEntity user
    ) {
        return new ResponseEntity<>(service.subscribe(id, user), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @GetMapping(value = "/{id}/unsubscribe")
    public ResponseEntity<InterviewDto> unsubscribe(
            @PathVariable Long id,
            @RequestAttribute UserEntity user
    ) {
        return new ResponseEntity<>(service.unsubscribe(id, user), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR', 'ROLE_DIRECTOR', 'ROLE_INTERVIEWER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR', 'ROLE_DIRECTOR', 'ROLE_INTERVIEWER')")
    @DeleteMapping()
    public ResponseEntity<Void> delete(
            @RequestParam(value = "ids") List<Long> ids
    ) {
        service.delete(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
