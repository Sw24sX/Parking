package com.example.parking.exceptions;

public class ParkingException extends RuntimeException {

    private static final long serialVersionUID = -433865786252853454L;

    public ParkingException(String message) {
        super(message);
    }
}
