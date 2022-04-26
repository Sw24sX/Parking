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

    public Booking create(Booking booking, Car car, Parking parking) {
        booking.setCar(car);
        booking.setParking(parking);
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

    public boolean existBookingAtPeriodDate(Long carId, Long parkingId, Date fromDate, Date toDate) {
        return bookingRepository.existsCarInBorderDate(carId, fromDate, toDate) ||
                bookingRepository.existsParkingInBorderDate(parkingId, fromDate, toDate);
    }
}
