package arden.java.islab1.api.controller;

import arden.java.islab1.api.dto.request.AddVehicleRequest;
import arden.java.islab1.api.dto.response.AddVehicleResponse;
import arden.java.islab1.service.impl.VehicleServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/vehicle")
@RequiredArgsConstructor
public class VehicleController {
    private final VehicleServiceImpl vehicleServiceImpl;

    @PostMapping("/add")
    public ResponseEntity<AddVehicleResponse> addVehicle(@RequestBody @Valid AddVehicleRequest addVehicleRequest) {
        return ResponseEntity.ok().body(vehicleServiceImpl.addVehicle(addVehicleRequest));
    }
}
