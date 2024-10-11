package arden.java.islab1.service;

import arden.java.islab1.api.dto.request.AddVehicleRequest;
import arden.java.islab1.api.dto.response.AddVehicleResponse;

public interface VehicleService {
    AddVehicleResponse addVehicle(AddVehicleRequest request);
}
