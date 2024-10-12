package arden.java.islab1.service;

import arden.java.islab1.model.vehicle.Coordinates;

import java.util.List;

public interface CoordinatesService {
    List<Coordinates> getAllCoordinates();
    Coordinates getCoordinatesById(Integer id);
}
