package com.example.parking.repository;

import com.example.parking.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface CarRepository extends JpaRepository<Car, Long> {

}