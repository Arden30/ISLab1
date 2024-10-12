package arden.java.islab1.service.impl;

import arden.java.islab1.api.dto.request.AddVehicleRequest;
import arden.java.islab1.api.dto.request.UpdateVehicleRequest;
import arden.java.islab1.api.dto.response.VehicleResponse;
import arden.java.islab1.exception.exceptions.NoSuchVehicleException;
import arden.java.islab1.exception.exceptions.NotYourVehicleException;
import arden.java.islab1.mapper.VehicleMapper;
import arden.java.islab1.model.vehicle.Coordinates;
import arden.java.islab1.model.vehicle.Vehicle;
import arden.java.islab1.repository.CoordinatesRepository;
import arden.java.islab1.repository.VehicleRepository;
import arden.java.islab1.service.UserService;
import arden.java.islab1.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleMapper vehicleMapper;
    private final VehicleRepository vehicleRepository;
    private final UserService userService;
    private final CoordinatesRepository coordinatesRepository;

    @Override
    public List<VehicleResponse> getAllVehicles() {
        return vehicleRepository.findAll().stream().map(vehicleMapper::toResponse).toList();
    }

    @Override
    public VehicleResponse getVehicleById(Long id) {
        return vehicleRepository.findById(id).map(vehicleMapper::toResponse).orElseThrow(() -> new NoSuchVehicleException("There is no vehicle with id " + id));
    }

    @Override
    public VehicleResponse addVehicle(AddVehicleRequest request) {
        Vehicle vehicle = vehicleMapper.toVehicle(request);
        Optional<Coordinates> existingCoordinates = coordinatesRepository.findAll().stream().filter(coordinates -> coordinates.getX() == request.coordinates().x() && coordinates.getY() == request.coordinates().y()).findAny();
        existingCoordinates.ifPresent(vehicle::setCoordinates);

        vehicle.setUser(userService.getCurrentUser());
        Vehicle vehicleFromDB = vehicleRepository.save(vehicle);

        return vehicleMapper.toResponse(vehicleFromDB);
    }

    @Override
    public VehicleResponse updateVehicle(UpdateVehicleRequest request) {
        Vehicle vehicle = vehicleMapper.toVehicle(request);
        Optional<Vehicle> vehicleFromDB = vehicleRepository.findById(vehicle.getId());
        if (vehicleFromDB.isEmpty()) {
            throw new NoSuchVehicleException("There is no vehicle with id " + request.id());
        } else if (!vehicleFromDB.get().getUser().getId().equals(userService.getCurrentUser().getId())) {
            throw new NotYourVehicleException("This vehicle belongs to another user, you can't update this vehicle");
        }

        vehicleRepository.deleteById(vehicle.getId());
        vehicle.setId(vehicleFromDB.get().getId());
        vehicle.setCreationDate(vehicleFromDB.get().getCreationDate());
        vehicle.setUser(userService.getCurrentUser());
        Vehicle vehicleToDB = vehicleRepository.save(vehicle);

        return vehicleMapper.toResponse(vehicleToDB);
    }

    @Override
    public VehicleResponse deleteVehicle(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(() -> new NoSuchVehicleException("There is no vehicle with id " + id));
        if (!vehicle.getUser().getId().equals(userService.getCurrentUser().getId())) {
            throw new NotYourVehicleException("This vehicle belongs to another user, you can't delete this vehicle");
        }

        vehicleRepository.delete(vehicle);

        return vehicleMapper.toResponse(vehicle);
    }
}
