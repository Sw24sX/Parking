package com.example.parking.model;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CAR")
public class Car extends BaseEntity {
    @NotNull
    @Column(name = "NUMBER")
    private String number;

    @NotNull
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
