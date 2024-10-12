package arden.java.islab1.service.impl;

import arden.java.islab1.api.dto.response.ChangeResponse;
import arden.java.islab1.exception.exceptions.NoSuchVehicleException;
import arden.java.islab1.model.user.Change;
import arden.java.islab1.model.user.User;
import arden.java.islab1.model.vehicle.Vehicle;
import arden.java.islab1.repository.ChangeRepository;
import arden.java.islab1.repository.VehicleRepository;
import arden.java.islab1.service.ChangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChangeServiceImpl implements ChangeService {
    private final VehicleRepository vehicleRepository;
    private final ChangeRepository changeRepository;

    @Override
    public List<ChangeResponse> getAllChangesByVehicleId(Long vehicleId) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(vehicleId);
        if (vehicle.isEmpty()) {
            throw new NoSuchVehicleException("There is no vehicle with id " + vehicleId);
        }

        return vehicle.get().getChange().stream().map(change -> new ChangeResponse(change.getUser().getUsername(), change.getModifiedAt())).toList();
    }

    @Override
    public Change addChangeToVehicle(Vehicle vehicle, User user) {
        Change change = new Change();
        change.setVehicle(vehicle);
        change.setUser(user);
        change.setModifiedAt(LocalDateTime.now());
        vehicle.getChange().add(change);

        changeRepository.save(change);

        return change;
    }
}
