package com.example.parking.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CAR")
public class Car extends BaseEntity {
    @Column(name = "NUMBER")
    private String number;

    @OneToMany(mappedBy = "car")
    private List<Booking> bookings = new ArrayList<>();

    public String getNumber() {
        return number;
    }

    public void setNumber(String name) {
        this.number = name;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}
