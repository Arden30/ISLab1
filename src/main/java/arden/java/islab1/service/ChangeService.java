package arden.java.islab1.service;

import arden.java.islab1.api.dto.response.ChangeResponse;
import arden.java.islab1.model.user.Change;
import arden.java.islab1.model.user.User;
import arden.java.islab1.model.vehicle.Vehicle;

import java.util.List;

public interface ChangeService {
    List<ChangeResponse> getAllChangesByVehicleId(Long vehicleId);

    Change addChangeToVehicle(Vehicle vehicle, User user);
}
