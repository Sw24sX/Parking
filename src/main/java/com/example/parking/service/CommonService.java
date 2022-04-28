package com.example.parking.service;

import com.example.parking.dto.BookingDto;
import com.example.parking.mapper.BookingMapper;
import com.example.parking.mapper.CarMapper;
import com.example.parking.mapper.ParkingMapper;
import com.example.parking.model.Booking;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommonService {
    private final CarService carService;
    private final ParkingService parkingService;
    private final BookingService bookingService;

    private final CarMapper carMapper;
    private final ParkingMapper parkingMapper;
    private final BookingMapper bookingMapper;

    public CommonService(CarService carService, ParkingService parkingService, BookingService bookingService,
                         CarMapper carMapper, ParkingMapper parkingMapper, BookingMapper bookingMapper) {
        this.carService = carService;
        this.parkingService = parkingService;
        this.bookingService = bookingService;
        this.carMapper = carMapper;
        this.parkingMapper = parkingMapper;
        this.bookingMapper = bookingMapper;
    }

    public List<BookingDto> getAll() {
        return bookingMapper.toListBookingDto(bookingService.getAll());
    }

    public BookingDto create(BookingDto dto) {
        Booking booking = bookingMapper.toBooking(dto);
        fillBooking(booking, dto);
        bookingService.validateBooking(booking, booking.getId());
        return bookingMapper.toBookingDto(bookingService.save(booking));
    }

    public BookingDto update(BookingDto dto, Long id) {
        Booking booking = bookingService.getById(id);
        bookingMapper.updateBookingFromDto(dto, booking);
        fillBooking(booking, dto);
        bookingService.validateBooking(booking, booking.getId());

        carMapper.updateCarFromDto(dto.getCar(), booking.getCar());
        parkingMapper.updateParkingFromDto(dto.getParking(), booking.getParking());
        carService.validate(booking.getCar());
        parkingService.validate(booking.getParking());

        return bookingMapper.toBookingDto(bookingService.save(booking));
    }

    public void delete(Long id) {
        Booking booking = bookingService.getById(id);
        bookingService.delete(id);
        if (!bookingService.existByCarId(booking.getCar().getId())) {
            carService.delete(booking.getCar().getId());
        }

        if (!bookingService.existByParkingId(booking.getParking().getId())) {
            parkingService.delete(booking.getParking().getId());
        }
    }

    private void fillBooking(Booking booking, BookingDto dto) {
        booking.setCar(dto.getCar().getId() == null ? carMapper.toCar(dto.getCar()) :
                carService.getById(dto.getCar().getId()));
        booking.setParking(dto.getParking().getId() == null ? parkingMapper.toParking(dto.getParking()) :
                parkingService.getById(dto.getParking().getId()));

        carService.validate(booking.getCar());
        parkingService.validate(booking.getParking());

        booking.getCar().getBookings().add(booking);
        booking.getParking().getBookings().add(booking);
    }
}
