package com.example.parking.dto;

import java.util.Date;

public class BookingDto extends BaseDto {
    private Date fromDate;

    private Date toDate;

    private ParkingDto parking;

    private CarDto car;

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public ParkingDto getParking() {
        return parking;
    }

    public void setParking(ParkingDto parking) {
        this.parking = parking;
    }

    public CarDto getCar() {
        return car;
    }

    public void setCar(CarDto car) {
        this.car = car;
    }
}
