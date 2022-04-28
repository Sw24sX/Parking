package com.example.parking.service;

import com.example.parking.exceptions.EntityNotFoundException;
import com.example.parking.exceptions.EntityNotValidException;
import com.example.parking.exceptions.Messages;
import com.example.parking.model.Booking;
import com.example.parking.repository.BookingRepository;
import org.springframework.stereotype.Service;

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
        return bookingRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
    }

    public Booking save(Booking booking) {
        return bookingRepository.save(booking);
    }

    public void delete(Long bookingId) {
        bookingRepository.deleteById(bookingId);
    }

    public void validateBooking(Booking booking, Long bookingId) {
        if (booking.getFromDate().after(booking.getToDate())) {
            throw new EntityNotValidException(Messages.INCORRECT_BOOKING_PERIOD);
        }

        List<Booking> bookingsByCar = bookingRepository.findBookingInBorderDateByCarId(booking.getCar().getId(),
                booking.getFromDate(), booking.getToDate());
        if (!bookingsByCar.isEmpty() && (bookingsByCar.size() > 1 || !bookingsByCar.get(0).getId().equals(bookingId))) {
            throw new EntityNotValidException(Messages.BOOKING_PERIOD_INTERSECTS_WITH_EXISTS);
        }

        List<Booking> bookingsByParking = bookingRepository.findBookingInBorderDateByParkingId(booking.getParking().getId(),
                booking.getFromDate(), booking.getToDate());
        if (!bookingsByParking.isEmpty() && (bookingsByParking.size() > 1 || !bookingsByParking.get(0).getId().equals(bookingId))) {
            throw new EntityNotValidException(Messages.BOOKING_PERIOD_INTERSECTS_WITH_EXISTS);
        }
    }

    public boolean existByCarId(Long carId) {
        return bookingRepository.existsByCar_Id(carId);
    }

    public boolean existByParkingId(Long parkingId) {
        return bookingRepository.existsByParking_Id(parkingId);
    }
}
