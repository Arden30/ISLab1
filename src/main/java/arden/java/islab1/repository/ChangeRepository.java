package arden.java.islab1.repository;

import arden.java.islab1.model.user.Change;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChangeRepository extends JpaRepository<Change, Integer> {
    @Transactional
    void deleteChangeByVehicleId(Long vehicleId);
}
