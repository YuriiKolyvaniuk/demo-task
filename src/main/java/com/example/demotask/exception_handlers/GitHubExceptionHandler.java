package com.example.demotask.exception_handlers;

import com.example.demotask.dto.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class GitHubExceptionHandler {

    @ExceptionHandler(value = {HttpClientErrorException.class})
    public ResponseEntity<ErrorResponse> handleException(HttpClientErrorException ex) {
        if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(404, "Not Found"));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(500, "Internal Server Error"));
        }
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ResponseEntity<ErrorResponse> handleNotAcceptableException(HttpMediaTypeNotAcceptableException ex) {
        ErrorResponse errorResponse = new ErrorResponse(406, "The requested media type is not supported. Please use only JSON format.");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(errorResponse, headers, HttpStatus.NOT_ACCEPTABLE);
    }
}
