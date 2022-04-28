package com.example.parking.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends ParkingException {
    public EntityNotFoundException(Long id) {
        super(String.format(Messages.ENTITY_NOT_FOUND.getMessage(), id));
    }
}
