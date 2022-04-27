package com.example.parking.service;

import com.example.parking.model.Booking;
import com.example.parking.model.Car;
import com.example.parking.model.Parking;
import com.example.parking.repository.BookingRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public List<Booking> getAll() {
        return bookingRepository.findAll();
    }

    public Booking getById(Long id) {
        return bookingRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Booking create(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Booking update(Booking booking, Long bookingId, Car car, Parking parking) {
        if (!bookingRepository.existsById(bookingId)) {
            throw new EntityExistsException();
        }

        booking.setId(bookingId);
        booking.setCar(car);
        booking.setParking(parking);
        return bookingRepository.save(booking);
    }

    public void delete(Long bookingId) {
        bookingRepository.deleteById(bookingId);
    }

    public List<Booking> existCarAtPeriodDate(Long carId, Date fromDate, Date toDate) {
        return bookingRepository.findBookingInBorderDateByCarId(carId, fromDate, toDate);
    }

    public List<Booking> existPeriodAtPeriodDate(Long parkingId, Date fromDate, Date toDate) {
        return bookingRepository.findBookingInBorderDateByParkingId(parkingId, fromDate, toDate);
    }

    public boolean existById(Long id) {
        return bookingRepository.existsById(id);
    }
}
