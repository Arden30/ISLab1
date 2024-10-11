package arden.java.islab1.mapper;

import arden.java.islab1.api.dto.request.AddVehicleRequest;
import arden.java.islab1.api.dto.request.UpdateVehicleRequest;
import arden.java.islab1.api.dto.response.VehicleResponse;
import arden.java.islab1.model.vehicle.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface VehicleMapper {
    @Mapping(source = "coordinates.x", target = "x")
    @Mapping(source = "coordinates.y", target = "y")
    VehicleResponse toResponse(Vehicle request);

    @Mapping(source = "coordinates.x", target = "coordinates.x")
    @Mapping(source = "coordinates.y", target = "coordinates.y")
    Vehicle toVehicle(AddVehicleRequest addVehicleRequest);

    Vehicle toVehicle(UpdateVehicleRequest updateVehicleRequest);
}
