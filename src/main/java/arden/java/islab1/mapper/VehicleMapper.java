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
    @Mapping(source = "request.coordinates.x", target = "x")
    @Mapping(source = "request.coordinates.y", target = "y")
    @Mapping(source = "username", target = "username")
    VehicleResponse toResponse(Vehicle request, String username);

    @Mapping(source = "addVehicleRequest.coordinates.x", target = "coordinates.x")
    @Mapping(source = "addVehicleRequest.coordinates.y", target = "coordinates.y")
    Vehicle toVehicle(AddVehicleRequest addVehicleRequest);

    Vehicle toVehicle(UpdateVehicleRequest updateVehicleRequest);
}
