package com.example.parking.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "BOOKING")
public class Booking extends BaseEntity {
    @Column(name = "FROM_DATE")
    private Date fromDate;

    @Column(name = "TO_DATE")
    private Date toDate;

    @ManyToOne
    @JoinColumn(name = "PARKING_ID")
    private Parking parking;

    @ManyToOne
    @JoinColumn(name = "CAR_ID")
    private Car car;

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

    public Parking getParking() {
        return parking;
    }

    public void setParking(Parking parking) {
        this.parking = parking;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
