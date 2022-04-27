package com.example.parking.repository;

import com.example.parking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Query("select b from Booking b where b.car.id = :carId and " +
            "(:fromDate <= b.fromDate and b.fromDate <= :toDate or " +
            ":fromDate <= b.toDate and b.toDate <= :toDate or " +
            "b.fromDate <= :fromDate and :toDate <= b.toDate)")
    List<Booking> findBookingInBorderDateByCarId(Long carId, Date fromDate, Date toDate);

    @Query("select b from Booking b where b.parking.id = :parkingId and " +
            "(:fromDate <= b.fromDate and b.fromDate <= :toDate or " +
            ":fromDate <= b.toDate and b.toDate <= :toDate or " +
            "b.fromDate <= :fromDate and :toDate <= b.toDate)")
    List<Booking> findBookingInBorderDateByParkingId(Long parkingId, Date fromDate, Date toDate);
}