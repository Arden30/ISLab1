package arden.java.islab1.service.impl;

import arden.java.islab1.exception.exceptions.NoSuchVehicleException;
import arden.java.islab1.model.vehicle.Coordinates;
import arden.java.islab1.repository.CoordinatesRepository;
import arden.java.islab1.service.CoordinatesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoordinatesServiceImpl implements CoordinatesService {
    private final CoordinatesRepository coordinatesRepository;

    @Override
    public Coordinates getCoordinatesById(Integer id) {
        return coordinatesRepository.findById(id).orElseThrow(() -> new NoSuchVehicleException("Coordinates with id " + id + " does not exist"));
    }

    @Override
    public List<Coordinates> getAllCoordinates() {
        return coordinatesRepository.findAll();
    }
}
