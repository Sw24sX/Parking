package com.example.parking.mapper;

import com.example.parking.dto.CarDto;
import com.example.parking.model.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarMapper {
    CarDto toCarDto(Car car);
    List<CarDto> toListCarDto(List<Car> cars);

    @Mapping(target = "id", ignore = true)
    Car toCar(CarDto carDto);
    List<Car> toListCar(List<CarDto> carDtos);
}
