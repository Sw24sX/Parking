package com.example.parking.service;

import com.example.parking.dto.BookingDto;
import com.example.parking.dto.request.ChangeDto;
import com.example.parking.dto.request.ReadDto;
import com.example.parking.mapper.BookingMapper;
import com.example.parking.mapper.CarMapper;
import com.example.parking.mapper.ParkingMapper;
import com.example.parking.model.Booking;
import com.example.parking.model.Car;
import com.example.parking.model.Parking;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
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

    public ReadDto getAll() {
        ReadDto result = new ReadDto();
        result.setCars(carMapper.toListCarDto(carService.getAll()));
        result.setParkings(parkingMapper.toListParkingDto(parkingService.getAll()));
        result.setBookings(bookingMapper.toListBookingDto(bookingService.getAll()));

        return result;
    }

    public ChangeDto create(ChangeDto dto) throws Exception {
        ChangeDto result = new ChangeDto();

        Car car = carService.create(carMapper.toCar(dto.getCar()));
        result.setCar(carMapper.toCarDto(car));

        Parking parking = parkingService.create(parkingMapper.toParking(dto.getParking()));
        result.setParking(parkingMapper.toParkingDto(parking));

        if (dto.getBooking() != null) {
            Booking booking = bookingMapper.toBooking(dto.getBooking());
            Long carId = Optional.ofNullable(dto.getBooking().getCar()).orElseThrow(EntityNotFoundException::new).getId();
            Long parkingId = Optional.ofNullable(dto.getBooking().getParking()).orElseThrow(EntityNotFoundException::new).getId();

            if (bookingService.existBookingAtPeriodDate(carId, parkingId, dto.getBooking().getFromDate(), dto.getBooking().getToDate())) {
                //TODO: throw custom exception
                throw new Exception();
            }

            Car bookingCar = carService.getById(carId);
            Parking bookingParking = parkingService.getById(parkingId);
            BookingDto bookingDto = bookingMapper.toBookingDto(bookingService.create(booking, car, parking));
            result.setBooking(bookingDto);
        }

        return result;
    }
}
