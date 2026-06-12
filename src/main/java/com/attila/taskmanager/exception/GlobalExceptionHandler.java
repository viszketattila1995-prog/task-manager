package com.attila.taskmanager.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(TaskAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handleTaskAlreadyExistsException(TaskAlreadyExistsException ex) {
        log.error("Task already exists: {}", ex.getMessage());
        return new ApiError("TASK_ALREADY_EXISTS", ex.getMessage(), "A task with the same name already exists.");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> methodArgumentNotValid(MethodArgumentNotValidException ex) {
        ValidationError validationError = new ValidationError();
        ex.getBindingResult().getFieldErrors().forEach(error -> validationError.addFieldError(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationError);
    }

    @ExceptionHandler(TaskNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleTaskNotFoundException(TaskNotFoundException ex) {
        log.error("Task not found: {}", ex.getMessage());
        return new ApiError("TASK_NOT_FOUND", ex.getMessage(), "Task with this id doesn't exists");
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handleUserAlreadyExists(UserAlreadyExistsException ex) {
        log.error("User already exists whit this username: {}", ex.getMessage());
        return new ApiError("USER_ALREADY_EXISTS", ex.getMessage(), "User with this username is already exists");
    }
}
