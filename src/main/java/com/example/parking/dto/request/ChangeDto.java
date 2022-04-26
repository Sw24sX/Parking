package com.example.parking.dto.request;

import com.example.parking.dto.BookingDto;
import com.example.parking.dto.CarDto;
import com.example.parking.dto.ParkingDto;

public class ChangeDto {
    private CarDto car;
    private ParkingDto parking;
    private BookingDto booking;

    public CarDto getCar() {
        return car;
    }

    public void setCar(CarDto car) {
        this.car = car;
    }

    public ParkingDto getParking() {
        return parking;
    }

    public void setParking(ParkingDto parking) {
        this.parking = parking;
    }

    public BookingDto getBooking() {
        return booking;
    }

    public void setBooking(BookingDto booking) {
        this.booking = booking;
    }
}
