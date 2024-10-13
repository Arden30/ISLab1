package arden.java.islab1.model.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "is_lab1_admin_requests")
@Data
public class AdminRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotNull
    Long userId;

    @Column(nullable = false, unique = true)
    @NotNull
    String username;
}
