package com.example.parking.service;

import com.example.parking.exceptions.EntityNotFoundException;
import com.example.parking.exceptions.EntityNotValidException;
import com.example.parking.exceptions.Messages;
import com.example.parking.model.Car;
import com.example.parking.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car getById(Long id) {
        return carRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
    }

    public void delete(Long id) {
        carRepository.deleteById(id);
    }

    public void validate(Car car) {
        List<Car> cars = carRepository.findAllByNumber(car.getNumber());
        if (!cars.isEmpty() && (cars.size() > 1 || !cars.get(0).getId().equals(car.getId()))) {
            throw new EntityNotValidException(Messages.CAR_EXISTED, car.getNumber());
        }
    }
}
