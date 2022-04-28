package com.example.parking.mapper;

import com.example.parking.dto.CarDto;
import com.example.parking.model.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CarMapper {
    CarDto toCarDto(Car car);

    @Mapping(target = "id", ignore = true)
    Car toCar(CarDto carDto);

    @Mapping(target = "id", ignore = true)
    void updateCarFromDto(CarDto dto, @MappingTarget Car car);
}
