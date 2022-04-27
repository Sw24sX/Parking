package com.example.parking.mapper;

import com.example.parking.dto.CarDto;
import com.example.parking.model.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarMapper {
    CarDto toCarDto(Car car);
    List<CarDto> toListCarDto(List<Car> cars);

    Car toCar(CarDto carDto);
    List<Car> toListCar(List<CarDto> carDtos);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "bookings", ignore = true)
    void updateCarFromDto(CarDto dto, @MappingTarget Car car);
}
