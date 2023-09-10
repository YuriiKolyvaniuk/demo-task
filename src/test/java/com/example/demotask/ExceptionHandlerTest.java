package com.example.demotask;

import com.example.demotask.dto.ErrorResponse;
import com.example.demotask.exception_handlers.GitHubExceptionHandler;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ExceptionHandlerTest {

        @Test
        public void testHandleNotAcceptableException() {
            HttpMediaTypeNotAcceptableException ex = new HttpMediaTypeNotAcceptableException(
                    Collections.singletonList(MediaType.APPLICATION_JSON));

            GitHubExceptionHandler handler = new GitHubExceptionHandler();
            ResponseEntity<ErrorResponse> response = handler.handleNotAcceptableException(ex);

            assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
            assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
            assertEquals(406, response.getBody().getStatus());
            assertEquals("The requested media type is not supported. Please use only JSON format.",
                    response.getBody().getMessage());
        }

    @Test
    public void testHandleExceptionNotFound() {
        HttpClientErrorException ex = new HttpClientErrorException(HttpStatus.NOT_FOUND);

        GitHubExceptionHandler handler = new GitHubExceptionHandler();
        ResponseEntity<ErrorResponse> response = handler.handleException(ex);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(404, response.getBody().getStatus());
        assertEquals("Not Found", response.getBody().getMessage());
    }

    @Test
    public void testHandleExceptionInternalError() {
        HttpClientErrorException ex = new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR);

        GitHubExceptionHandler handler = new GitHubExceptionHandler();
        ResponseEntity<ErrorResponse> response = handler.handleException(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(500, response.getBody().getStatus());
        assertEquals("Internal Server Error", response.getBody().getMessage());
    }

}
