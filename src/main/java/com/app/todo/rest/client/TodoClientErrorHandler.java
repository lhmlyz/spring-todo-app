package com.app.todo.rest.client;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

@Component
public class TodoClientErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        if (response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            throw new ResourceNotFound("Resource not found!" + response.getStatusText());

        }

        if (response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
            throw new RuntimeException("Server side error");
        }
    }
}

