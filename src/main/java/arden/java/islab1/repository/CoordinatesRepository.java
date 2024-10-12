package arden.java.islab1.repository;

import arden.java.islab1.model.vehicle.Coordinates;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoordinatesRepository extends JpaRepository<Coordinates, Integer> {
}
