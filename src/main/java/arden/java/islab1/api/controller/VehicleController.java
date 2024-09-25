package arden.java.islab1.api.controller;

import arden.java.islab1.api.dto.request.AddVehicleRequest;
import arden.java.islab1.service.VehicleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class VehicleController {
    private final VehicleService vehicleService;

    @PostMapping("/vehicle")
    public ResponseEntity<?> addVehicle(@Valid @RequestBody AddVehicleRequest addVehicleRequest) {
        return ResponseEntity.ok().body(vehicleService.addVehicle(addVehicleRequest));
    }
}
