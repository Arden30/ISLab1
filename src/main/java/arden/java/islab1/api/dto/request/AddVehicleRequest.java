package arden.java.islab1.api.dto.request;

import arden.java.islab1.model.FuelType;
import arden.java.islab1.model.VehicleType;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

public record AddVehicleRequest(
        @NotBlank(message = "Name of vehicle must have at least 1 non-whitespace character")
        String name,
        @NotNull
        @Valid
        CoordinatesRequest coordinates,
        @Nullable
        VehicleType vehicleType,
        @Positive(message = "engine power is a positive value")
        double enginePower,
        @Positive(message = "number of wheels is a positive value")
        double numberOfWheels,
        @Nullable
        @Positive(message = "capacity is a positive value")
        Double capacity,
        @Nullable
        @Positive(message = "distance travelled is a positive value")
        Double distanceTravelled,
        @Nullable
        @Positive(message = "fuel consumption is a positive value")
        Double fuelConsumption,
        @NotNull(message = "fuel type is mandatory to enter")
        FuelType fuelType
) {

    public record CoordinatesRequest(
            @DecimalMin(value = "-118.0", message = "Minimum value of X is -118")
            double x,
            @DecimalMin(value = "-652.0", message = "Minimum value of X is -652")
            double y
    ) {
    }

}
