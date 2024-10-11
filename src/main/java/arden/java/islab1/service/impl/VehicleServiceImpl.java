package arden.java.islab1.service.impl;

import arden.java.islab1.api.dto.request.AddVehicleRequest;
import arden.java.islab1.api.dto.response.AddVehicleResponse;
import arden.java.islab1.mapper.VehicleMapper;
import arden.java.islab1.model.vehicle.Vehicle;
import arden.java.islab1.repository.VehicleRepository;
import arden.java.islab1.service.UserService;
import arden.java.islab1.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {
    private final VehicleMapper vehicleMapper;
    private final VehicleRepository vehicleRepository;
    private final UserService userService;

    public AddVehicleResponse addVehicle(AddVehicleRequest request) {
        Vehicle vehicle = vehicleMapper.toVehicle(request);
        vehicle.setUser(userService.getCurrentUser());
        Vehicle vehicleFromDB = vehicleRepository.save(vehicle);

        return vehicleMapper.toResponse(vehicleFromDB);
    }
}
