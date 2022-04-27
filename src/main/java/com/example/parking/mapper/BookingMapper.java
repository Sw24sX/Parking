package com.example.parking.mapper;

import com.example.parking.dto.BookingDto;
import com.example.parking.model.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CarMapper.class, ParkingMapper.class})
public interface BookingMapper {
    BookingDto toBookingDto(Booking booking);
    List<BookingDto> toListBookingDto(List<Booking> bookings);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "parking", ignore = true)
    @Mapping(target = "car", ignore = true)
    Booking toBooking(BookingDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "parking", ignore = true)
    @Mapping(target = "car", ignore = true)
    void updateBookingFromDto(BookingDto dto, @MappingTarget Booking booking);
}
