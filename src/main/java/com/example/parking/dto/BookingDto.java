package com.example.parking.dto;

import com.sun.istack.NotNull;

import java.util.Date;

public class BookingDto extends BaseDto {
    @NotNull
    private Date fromDate;

    @NotNull
    private Date toDate;

    @NotNull
    private ParkingDto parking;

    @NotNull
    private CarDto car;

    @NotNull
    private int price;

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
