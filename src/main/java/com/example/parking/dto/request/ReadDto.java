package com.example.parking.dto.request;

import com.example.parking.dto.BookingDto;
import com.example.parking.dto.CarDto;
import com.example.parking.dto.ParkingDto;

import java.util.List;

public class ReadDto {
    private List<CarDto> cars;

    private List<ParkingDto> parkings;

    private List<BookingDto> bookings;

    public List<CarDto> getCars() {
        return cars;
    }

    public void setCars(List<CarDto> cars) {
        this.cars = cars;
    }

    public List<ParkingDto> getParkings() {
        return parkings;
    }

    public void setParkings(List<ParkingDto> parkings) {
        this.parkings = parkings;
    }

    public List<BookingDto> getBookings() {
        return bookings;
    }

    public void setBookings(List<BookingDto> bookings) {
        this.bookings = bookings;
    }
}
