package arden.java.islab1.api.controller;

import arden.java.islab1.api.dto.request.AddVehicleRequest;
import arden.java.islab1.api.dto.request.UpdateVehicleRequest;
import arden.java.islab1.api.dto.response.VehicleResponse;
import arden.java.islab1.service.VehicleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicle")
@RequiredArgsConstructor
public class VehicleController {
    private final VehicleService vehicleService;

    @GetMapping("/get")
    public ResponseEntity<List<VehicleResponse>> getAllVehicle() {
        return ResponseEntity.ok().body(vehicleService.getAllVehicles());
    }

    @PostMapping("/add")
    public ResponseEntity<VehicleResponse> addVehicle(@RequestBody @Valid AddVehicleRequest addVehicleRequest) {
        return ResponseEntity.ok().body(vehicleService.addVehicle(addVehicleRequest));
    }

    @PutMapping
    public ResponseEntity<VehicleResponse> updateVehicle(@RequestBody @Valid UpdateVehicleRequest updateVehicleRequest) {
        return ResponseEntity.ok().body(vehicleService.updateVehicle(updateVehicleRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<VehicleResponse> deleteVehicle(@PathVariable Long id) {
        return ResponseEntity.ok().body(vehicleService.deleteVehicle(id));
    }
}
