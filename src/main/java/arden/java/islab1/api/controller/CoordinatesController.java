package arden.java.islab1.api.controller;

import arden.java.islab1.model.vehicle.Coordinates;
import arden.java.islab1.service.CoordinatesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/coordinates")
@RequiredArgsConstructor
public class CoordinatesController {
    private final CoordinatesService coordinatesService;

    @GetMapping("/all")
    public ResponseEntity<List<Coordinates>> getAllCoordinates() {
        return ResponseEntity.ok(coordinatesService.getAllCoordinates());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Coordinates> getCoordinates(@PathVariable int id) {
        return ResponseEntity.ok(coordinatesService.getCoordinatesById(id));
    }
}
