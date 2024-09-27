package arden.java.islab1.mapper;

import arden.java.islab1.api.dto.request.AddVehicleRequest;
import arden.java.islab1.api.dto.response.AddVehicleResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface VehicleMapper {
    @Mapping(source = "coordinates.x", target = "x")
    @Mapping(source = "coordinates.y", target = "y")
    AddVehicleResponse toResponse(AddVehicleRequest request);
}
