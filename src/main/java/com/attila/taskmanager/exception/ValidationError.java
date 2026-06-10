package com.attila.taskmanager.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ValidationError {

    private final List<FieldErrorDTO> fieldErrors = new ArrayList<>();

    public void addFieldError(String path, String message) {
        FieldErrorDTO error = new FieldErrorDTO(path, message);
        fieldErrors.add(error);
    }

    @Getter
    @AllArgsConstructor
    public static class FieldErrorDTO {

        private String field;
        private String message;

    }
}
