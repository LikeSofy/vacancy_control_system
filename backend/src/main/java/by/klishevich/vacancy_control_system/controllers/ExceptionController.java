package by.klishevich.vacancy_control_system.controllers;

import by.klishevich.vacancy_control_system.entity.ErrorDto;
import by.klishevich.vacancy_control_system.exceptions.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorDto> handleMethodArgumentNotValidException(BadRequestException e) {
        ErrorDto response = new ErrorDto(e.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
