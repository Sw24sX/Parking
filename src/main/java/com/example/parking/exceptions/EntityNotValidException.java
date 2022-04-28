package com.example.parking.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EntityNotValidException extends ParkingException {
    public EntityNotValidException(Messages message, String... args) {
        super(String.format(message.getMessage(), args));
    }
}
