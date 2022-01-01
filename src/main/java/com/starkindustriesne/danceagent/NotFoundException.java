package com.starkindustriesne.danceagent;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotFoundException extends ResponseStatusException {
    public NotFoundException() {
        super(HttpStatus.NOT_FOUND, "The requested object was not found.");
    }

    public NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
