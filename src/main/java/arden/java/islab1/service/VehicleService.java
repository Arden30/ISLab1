package arden.java.islab1.service;

import arden.java.islab1.api.dto.request.AddVehicleRequest;
import arden.java.islab1.api.dto.request.UpdateVehicleRequest;
import arden.java.islab1.api.dto.response.VehicleResponse;

public interface VehicleService {
    VehicleResponse addVehicle(AddVehicleRequest request);
    VehicleResponse updateVehicle(UpdateVehicleRequest request);
    VehicleResponse deleteVehicle(Long id);
}
