package com.epam.lab.dto;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class ApiErrorDTO {

    private HttpStatus status;
    private String message;
    private List<String> errors;

    public ApiErrorDTO(HttpStatus status, String message, List<String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public ApiErrorDTO(HttpStatus status, String message, String error) {
        super();
        this.status = status;
        this.message = message;
        errors = new ArrayList<>();
        errors.add(error);
    }

    public HttpStatus getStatus() {
        return status;
    }

    public ApiErrorDTO setStatus(HttpStatus status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ApiErrorDTO setMessage(String message) {
        this.message = message;
        return this;
    }

    public List<String> getErrors() {
        return errors;
    }

    public ApiErrorDTO setErrors(List<String> errors) {
        this.errors = errors;
        return this;
    }
}
