package com.wagner.event_schedule.exceptions.handler;

import com.wagner.event_schedule.dtos.v1.responses.ExceptionResponseDto;
import com.wagner.event_schedule.dtos.v1.responses.ValidationErrorResponseDto;
import com.wagner.event_schedule.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalHandlerException {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponseDto> handleAllExceptions(
            Exception ex, WebRequest request) {

        ExceptionResponseDto exceptionResponse = new ExceptionResponseDto(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<ValidationErrorResponseDto> handleValidationExceptions(
            MethodArgumentNotValidException ex) {

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        List<String> errors = fieldErrors.stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.toList());

        ValidationErrorResponseDto validationErrorResponse = new ValidationErrorResponseDto(
                new Date(),
                "Erro de validação",
                errors
        );

        return new ResponseEntity<>(validationErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ExceptionResponseDto> handleNotFoundExceptions(
            Exception ex, WebRequest request) {

        ExceptionResponseDto exceptionResponse = new ExceptionResponseDto(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
}
