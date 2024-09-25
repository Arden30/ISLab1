package arden.java.islab1.api.dto.response;

import arden.java.islab1.model.vehicle.FuelType;
import arden.java.islab1.model.vehicle.VehicleType;

import java.util.Date;

public record AddVehicleResponse(
        Long id,
        String name,
        double x,
        double y,
        Date creationDate,
        VehicleType vehicleType,
        double enginePower,
        int numOfWheels,
        double capacity,
        double distanceTravelled,
        double fuelConsumption,
        FuelType fuelType
) {
}
