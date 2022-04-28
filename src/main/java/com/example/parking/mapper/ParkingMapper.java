package com.example.parking.mapper;

import com.example.parking.dto.ParkingDto;
import com.example.parking.model.Parking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ParkingMapper {
    ParkingDto toParkingDto(Parking parking);

    @Mapping(target = "id", ignore = true)
    Parking toParking(ParkingDto parkingDto);

    @Mapping(target = "id", ignore = true)
    void updateParkingFromDto(ParkingDto parkingDto, @MappingTarget Parking parking);
}
