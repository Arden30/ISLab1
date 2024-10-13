package arden.java.islab1.service.impl;

import arden.java.islab1.api.dto.request.AddVehicleRequest;
import arden.java.islab1.api.dto.request.UpdateVehicleRequest;
import arden.java.islab1.api.dto.response.VehicleResponse;
import arden.java.islab1.exception.exceptions.NoSuchVehicleException;
import arden.java.islab1.exception.exceptions.NotYourVehicleException;
import arden.java.islab1.mapper.VehicleMapper;
import arden.java.islab1.model.user.User;
import arden.java.islab1.model.vehicle.Coordinates;
import arden.java.islab1.model.vehicle.Vehicle;
import arden.java.islab1.repository.ChangeRepository;
import arden.java.islab1.repository.CoordinatesRepository;
import arden.java.islab1.repository.VehicleRepository;
import arden.java.islab1.service.ChangeService;
import arden.java.islab1.service.RoleService;
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
    private final ChangeService changeService;
    private final RoleService roleService;
    private final ChangeRepository changeRepository;

    @Override
    public List<VehicleResponse> getAllVehicles() {
        String username = userService.getCurrentUser().getUsername();
        return vehicleRepository.findAll().stream().map(vehicle -> vehicleMapper.toResponse(vehicle, username, vehicle.isCouldBeChanged())).toList();
    }

    @Override
    public VehicleResponse getVehicleById(Long id) {
        String username = userService.getCurrentUser().getUsername();
        return vehicleRepository.findById(id).map(vehicle -> vehicleMapper.toResponse(vehicle, username, vehicle.isCouldBeChanged())).orElseThrow(() -> new NoSuchVehicleException("There is no vehicle with id " + id));
    }

    @Override
    public VehicleResponse addVehicle(AddVehicleRequest request) {
        Vehicle vehicle = vehicleMapper.toVehicle(request);
        Optional<Coordinates> existingCoordinates = coordinatesRepository.findAll().stream().filter(coordinates -> coordinates.getX() == request.coordinates().x() && coordinates.getY() == request.coordinates().y()).findAny();
        existingCoordinates.ifPresent(vehicle::setCoordinates);

        vehicle.setUser(userService.getCurrentUser());
        Vehicle vehicleFromDB = vehicleRepository.save(vehicle);

        return vehicleMapper.toResponse(vehicleFromDB, vehicle.getUser().getUsername(), vehicle.isCouldBeChanged());
    }

    @Override
    public VehicleResponse updateVehicle(UpdateVehicleRequest request) {
        Vehicle vehicle = vehicleMapper.toVehicle(request);
        Optional<Vehicle> vehicleFromDB = vehicleRepository.findById(vehicle.getId());
        if (vehicleFromDB.isEmpty()) {
            throw new NoSuchVehicleException("There is no vehicle with id " + request.id());
        } else if (!userService.getCurrentUser().getRoles().contains(roleService.getAdminRole()) || !vehicleFromDB.get().isCouldBeChanged()) {
            if (!vehicleFromDB.get().getUser().getId().equals(userService.getCurrentUser().getId())) {
                throw new NotYourVehicleException("This vehicle belongs to another user, you can't update this vehicle");
            }
        }

        vehicle.setId(vehicleFromDB.get().getId());
        vehicle.setCreationDate(vehicleFromDB.get().getCreationDate());
        vehicle.setUser(userService.getCurrentUser());
        vehicle.setChange(vehicleFromDB.get().getChange());
        Optional<Coordinates> existingCoordinates = coordinatesRepository.findAll().stream().filter(coordinates -> coordinates.getX() == request.coordinates().x() && coordinates.getY() == request.coordinates().y()).findAny();
        existingCoordinates.ifPresent(vehicle::setCoordinates);

        Vehicle vehicleToDB = vehicleRepository.save(vehicle);
        User currentUser = userService.getCurrentUser();
        changeService.addChangeToVehicle(vehicleToDB, currentUser);

        return vehicleMapper.toResponse(vehicleToDB, vehicle.getUser().getUsername(), vehicle.isCouldBeChanged());
    }

    @Override
    public VehicleResponse deleteVehicle(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(() -> new NoSuchVehicleException("There is no vehicle with id " + id));
        if (!userService.getCurrentUser().getRoles().contains(roleService.getAdminRole()) || !vehicle.isCouldBeChanged()) {
            if (!vehicle.getUser().getId().equals(userService.getCurrentUser().getId())) {
                throw new NotYourVehicleException("This vehicle belongs to another user, you can't delete this vehicle");
            }
        }

        changeRepository.deleteChangeByVehicleId(vehicle.getId());
        vehicleRepository.delete(vehicle);

        return vehicleMapper.toResponse(vehicle, vehicle.getUser().getUsername(), vehicle.isCouldBeChanged());
    }
}
