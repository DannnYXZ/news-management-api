package com.epam.lab.exception;

import com.epam.lab.dto.ApiErrorDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
        HttpHeaders headers,
        HttpStatus status,
        WebRequest request) {
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("path", ((ServletWebRequest) request).getRequest().getRequestURI());
        responseBody.put("message", "The URL you have reached is not in service at this time (404).");
        return new ResponseEntity<>(responseBody, headers, status);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
        WebRequest request) {
        String error = "wrong " + ex.getName() + " parameter format";
        ApiErrorDTO apiError = new ApiErrorDTO(HttpStatus.BAD_REQUEST, null, error);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({EntityExistsException.class})
    public ResponseEntity<Object> handleEntityExists(RuntimeException ex, WebRequest request) {
        ApiErrorDTO apiError = new ApiErrorDTO(HttpStatus.BAD_REQUEST, "Entity already exists.",
            ex.getLocalizedMessage());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({InsufficientEntityDataException.class})
    public ResponseEntity<Object> handleInsufficientData(RuntimeException ex, WebRequest request) {
        ApiErrorDTO apiError = new ApiErrorDTO(HttpStatus.BAD_REQUEST, "Something is missing :(",
            ex.getLocalizedMessage());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({TagsLinkageException.class})
    public ResponseEntity<Object> handleTagsLinkageException(TagsLinkageException ex, WebRequest request) {
        ApiErrorDTO apiError = new ApiErrorDTO(HttpStatus.CONFLICT, "Tag linkage problems :(", new ArrayList<>());
        apiError.getErrors().addAll(ex.getExceptions().stream().map(e -> e.getMessage()).collect(Collectors.toList()));
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler(value = {EntityNotFoundException.class})
    protected ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        ApiErrorDTO apiError = new ApiErrorDTO(HttpStatus.NOT_FOUND, "error occurred", ex.getLocalizedMessage());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
        ApiErrorDTO apiError = new ApiErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR, null, "Internal Server Error");
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
        HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiErrorDTO apiError = new ApiErrorDTO(HttpStatus.BAD_REQUEST, "Entity constraints conflict.",
            new ArrayList<>());
        ex.getBindingResult().getAllErrors().forEach(error -> {
            apiError.getErrors().add(error.getDefaultMessage());
        });
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }
}
