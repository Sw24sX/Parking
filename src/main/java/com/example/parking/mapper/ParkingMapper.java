package com.example.parking.mapper;

import com.example.parking.dto.ParkingDto;
import com.example.parking.model.Parking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ParkingMapper {
    ParkingDto toParkingDto(Parking parking);
    List<ParkingDto> toListParkingDto(List<Parking> parkings);

    Parking toParking(ParkingDto parkingDto);
    List<Parking> toListParking(List<ParkingDto> parkingDtos);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "bookings", ignore = true)
    void updateParkingFromDto(ParkingDto parkingDto, @MappingTarget Parking parking);
}
