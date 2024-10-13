package arden.java.islab1.api.controller;

import arden.java.islab1.api.dto.response.ChangeResponse;
import arden.java.islab1.service.ChangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/changes")
@RequiredArgsConstructor
public class ChangeController {
    private final ChangeService changeService;

    @GetMapping("/{vehicleId}")
    public ResponseEntity<List<ChangeResponse>> getChangesByVehicleId(@PathVariable Long vehicleId) {
        return ResponseEntity.ok(changeService.getAllChangesByVehicleId(vehicleId));
    }
}
