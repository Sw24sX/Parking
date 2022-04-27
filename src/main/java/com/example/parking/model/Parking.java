package com.example.parking.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PARKING")
public class Parking extends BaseEntity {
    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "parking")
    private List<Booking> bookings = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}
