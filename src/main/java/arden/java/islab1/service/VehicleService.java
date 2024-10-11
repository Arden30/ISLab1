package arden.java.islab1.service;

import arden.java.islab1.api.dto.request.AddVehicleRequest;
import arden.java.islab1.api.dto.request.UpdateVehicleRequest;
import arden.java.islab1.api.dto.response.VehicleResponse;

import java.util.List;

public interface VehicleService {
    VehicleResponse addVehicle(AddVehicleRequest request);

    List<VehicleResponse> getAllVehicles();

    VehicleResponse getVehicleById(Long id);

    VehicleResponse updateVehicle(UpdateVehicleRequest request);

    VehicleResponse deleteVehicle(Long id);
}
