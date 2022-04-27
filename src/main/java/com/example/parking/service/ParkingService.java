package com.example.parking.service;

import com.example.parking.model.Parking;
import com.example.parking.repository.ParkingRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ParkingService {
    private final ParkingRepository parkingRepository;

    public ParkingService(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }

    public List<Parking> getAll() {
        return parkingRepository.findAll();
    }

    public Parking getById(Long id) {
        return parkingRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Parking create(Parking parking) {
        return parkingRepository.save(parking);
    }

    public Parking update(Parking parking, Long id) {
        if (!parkingRepository.existsById(id)) {
            throw new EntityExistsException();
        }

        parking.setId(id);
        return parkingRepository.save(parking);
    }

    public void delete(Long id) {
        //TODO check exist
        parkingRepository.deleteById(id);
    }

    public boolean existById(Long id) {
        return parkingRepository.existsById(id);
    }
}
