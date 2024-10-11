package arden.java.islab1.api.controller;

import arden.java.islab1.api.dto.request.AddVehicleRequest;
import arden.java.islab1.api.dto.request.UpdateVehicleRequest;
import arden.java.islab1.api.dto.response.VehicleResponse;
import arden.java.islab1.service.impl.VehicleServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vehicle")
@RequiredArgsConstructor
public class VehicleController {
    private final VehicleServiceImpl vehicleServiceImpl;

    @PostMapping("/add")
    public ResponseEntity<VehicleResponse> addVehicle(@RequestBody @Valid AddVehicleRequest addVehicleRequest) {
        return ResponseEntity.ok().body(vehicleServiceImpl.addVehicle(addVehicleRequest));
    }

    @PutMapping
    public ResponseEntity<VehicleResponse> updateVehicle(@RequestBody @Valid UpdateVehicleRequest updateVehicleRequest) {
        return ResponseEntity.ok().body(vehicleServiceImpl.updateVehicle(updateVehicleRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<VehicleResponse> deleteVehicle(@PathVariable Long id) {
        return ResponseEntity.ok().body(vehicleServiceImpl.deleteVehicle(id));
    }
}
