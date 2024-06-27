package com.example.carparkingapi.config;

import com.example.carparkingapi.exception.not.found.*;
import com.example.carparkingapi.exception.other.InvalidFieldNameException;
import com.example.carparkingapi.exception.parking.*;
import com.example.carparkingapi.exception.security.InvalidCredentialsException;
import com.example.carparkingapi.exception.security.UserNotAuthenticatedException;
import com.example.carparkingapi.model.ApiError;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {CarNotFoundException.class, ParkingNotFoundException.class, UsernameNotFoundException.class,
            CustomerNotFoundException.class, AdminNotFoundException.class, NoCarsFoundException.class, UserNotFoundException.class})
    protected ResponseEntity<ApiError> handleNotFoundException(RuntimeException runtimeException) {
        return new ResponseEntity<>(new ApiError(HttpStatus.NOT_FOUND, runtimeException.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {InvalidCredentialsException.class, UserNotAuthenticatedException.class,
            AccessDeniedException.class})
    protected ResponseEntity<ApiError> handleAuthenticationException(RuntimeException runtimeException) {
        return new ResponseEntity<>(new ApiError(HttpStatus.FORBIDDEN, runtimeException.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {CarParkingStatusException.class, FullParkingException.class,
            ParkingSpaceToSmallException.class, LPGNotAllowedException.class, NoMoreElectricPlacesException.class})
    protected ResponseEntity<ApiError> handleParkingActionException(RuntimeException runtimeException) {
        return new ResponseEntity<>(new ApiError(HttpStatus.BAD_REQUEST, runtimeException.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {InvalidFieldNameException.class})
    protected ResponseEntity<ApiError> handleInvalidFieldNameException(RuntimeException runtimeException) {
        return new ResponseEntity<>(new ApiError(HttpStatus.BAD_REQUEST, runtimeException.getMessage()),
                HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = ConstraintViolationException.class)
    protected ResponseEntity<ApiError> handleConstraintViolation(ConstraintViolationException e) {
        List<String> errors = e.getConstraintViolations().stream()
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .toList();

        return new ResponseEntity<>(new ApiError(HttpStatus.BAD_REQUEST, errors), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected @NotNull ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
                                                                           @NotNull HttpHeaders headers,
                                                                           @NotNull HttpStatus status,
                                                                           @NotNull WebRequest request) {

        List<String> errors = e.getBindingResult().getAllErrors().stream()
                .map(error -> error instanceof FieldError fieldError ? fieldError.getField() + ": " +
                        fieldError.getDefaultMessage() : error.getDefaultMessage())
                .toList();

        return new ResponseEntity<>(new ApiError(HttpStatus.BAD_REQUEST, errors), headers, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected @NotNull ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException e,
                                                                           @NotNull HttpHeaders headers,
                                                                           @NotNull HttpStatus status,
                                                                           @NotNull WebRequest request) {

        if (e.getCause() instanceof InvalidFormatException ife) {
            String detailedError = ife.getPath().stream()
                    .map(ref -> ref.getFieldName() + ": Invalid format or value")
                    .collect(Collectors.joining(", "));
            return new ResponseEntity<>(new ApiError(HttpStatus.BAD_REQUEST, detailedError), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new ApiError(status, "Invalid request body"), headers, status);
    }
}