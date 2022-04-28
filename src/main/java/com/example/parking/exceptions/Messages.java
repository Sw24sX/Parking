package com.example.parking.exceptions;

public enum Messages {
    ENTITY_NOT_FOUND("Сущность с id %s не найдена"),

    INCORRECT_BOOKING_PERIOD("Некорректный период бронирования"),
    BOOKING_PERIOD_INTERSECTS_WITH_EXISTS("Период бронирования пересекается с существующим"),
    CAR_EXISTED("Автомобиль с номером %s уже существует"),
    PARKING_EXISTED("Парковочное место с названием %s уже существует");

    private final String message;

    Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
