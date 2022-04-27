package com.example.parking.service;

import com.example.parking.dto.BookingDto;
import com.example.parking.mapper.BookingMapper;
import com.example.parking.mapper.CarMapper;
import com.example.parking.mapper.ParkingMapper;
import com.example.parking.model.Booking;
import com.example.parking.model.Car;
import com.example.parking.model.Parking;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CommonService {
    private final CarService carService;
    private final ParkingService parkingService;
    private final BookingService bookingService;

    private final CarMapper carMapper;
    private final ParkingMapper parkingMapper;
    private final BookingMapper bookingMapper;

    public CommonService(CarService carService, ParkingService parkingService, BookingService bookingService, CarMapper carMapper, ParkingMapper parkingMapper, BookingMapper bookingMapper) {
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

    public BookingDto create(BookingDto dto) throws Exception {
        if (dto == null) {
            //TODO: throw custom exception
            throw new Exception();
        }

        checkBooking(dto, dto.getId());
        Booking booking = bookingMapper.toBooking(dto);
        fillBooking(booking, dto);
        return bookingMapper.toBookingDto(bookingService.create(booking));
    }

    private void checkBooking(BookingDto dto, Long id) throws Exception {
        List<Booking> bookingsByCar = bookingService.existCarAtPeriodDate(dto.getCar().getId(), dto.getFromDate(), dto.getToDate());
        if (!bookingsByCar.isEmpty() && (bookingsByCar.size() > 1 || !bookingsByCar.get(0).getId().equals(id))) {
            //TODO: throw custom exception
            throw new Exception();
        }

        List<Booking> bookingsByParking = bookingService.existPeriodAtPeriodDate(dto.getParking().getId(), dto.getFromDate(), dto.getToDate());
        if (!bookingsByParking.isEmpty() && (bookingsByParking.size() > 1 || !bookingsByParking.get(0).getId().equals(id))) {
            //TODO: throw custom exception
            throw new Exception();
        }
    }

    private void fillBooking(Booking booking, BookingDto dto) {
        Car car = dto.getCar().getId() == null ? carMapper.toCar(dto.getCar()) :
                carService.getById(dto.getCar().getId());
        Parking parking = dto.getParking().getId() == null ? parkingMapper.toParking(dto.getParking()) :
                parkingService.getById(dto.getParking().getId());

        car.getBookings().add(booking);
        parking.getBookings().add(booking);

        booking.setCar(car);
        booking.setParking(parking);
    }

    public BookingDto update(BookingDto dto, Long id) throws Exception {
        if (dto == null) {
            //TODO: throw custom exception
            throw new Exception();
        }

        Booking booking = bookingService.getById(id);
        checkBooking(dto, id);
        bookingMapper.updateBookingFromDto(dto, booking);
        fillBooking(booking, dto);

        carMapper.updateCarFromDto(dto.getCar(), booking.getCar());
        parkingMapper.updateParkingFromDto(dto.getParking(), booking.getParking());

        return bookingMapper.toBookingDto(bookingService.create(booking));
    }

    public void delete(Long id) {
        bookingService.delete(id);
    }
}
