package com.example.parking.service;

import com.example.parking.exceptions.EntityNotFoundException;
import com.example.parking.exceptions.EntityNotValidException;
import com.example.parking.exceptions.Messages;
import com.example.parking.model.Parking;
import com.example.parking.repository.ParkingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingService {
    private final ParkingRepository parkingRepository;

    public ParkingService(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }

    public Parking getById(Long id) {
        return parkingRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
    }

    public void delete(Long id) {
        parkingRepository.deleteById(id);
    }

    public void validate(Parking parking) {
        List<Parking> parkings = parkingRepository.findAllByName(parking.getName());
        if (!parkings.isEmpty() && (parkings.size() > 1 || !parkings.get(0).getId().equals(parking.getId()))) {
            throw new EntityNotValidException(Messages.PARKING_EXISTED, parking.getName());
        }
    }
}
