package arden.java.islab1.service;

import arden.java.islab1.api.dto.request.AddVehicleRequest;
import arden.java.islab1.api.dto.response.AddVehicleResponse;
import arden.java.islab1.mapper.VehicleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VehicleService {
    private final VehicleMapper vehicleMapper;

    public AddVehicleResponse addVehicle(AddVehicleRequest request) {
        return vehicleMapper.toResponse(request);
    }
}
