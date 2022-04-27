package com.example.parking.service;

import com.example.parking.model.Car;
import com.example.parking.repository.CarRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CarService {
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getAll() {
        return carRepository.findAll();
    }

    public Car getById(Long id) {
        return carRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Car create(Car car) {
        if (car == null) {
            return null;
        }

        //todo check by name
        return carRepository.save(car);
    }

    public Car update(Car car, Long id) {
        if (!carRepository.existsById(id)) {
            throw new EntityExistsException();
        }

        car.setId(id);
        return carRepository.save(car);
    }

    public void delete(Long id) {
        //todo check by id
        carRepository.deleteById(id);
    }

    public boolean existById(Long id) {
        return carRepository.existsById(id);
    }
}
