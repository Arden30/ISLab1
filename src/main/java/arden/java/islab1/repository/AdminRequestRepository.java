package arden.java.islab1.repository;

import arden.java.islab1.model.user.AdminRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRequestRepository extends JpaRepository<AdminRequest, Long> {
    Optional<AdminRequest> findByUserId(Long userId);
}
