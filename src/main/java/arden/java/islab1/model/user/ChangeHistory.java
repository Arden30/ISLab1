package arden.java.islab1.model.user;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "is_lab1_change_history")
@Data
public class ChangeHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "vehicle_id", nullable = false)
    private Long vehicleId;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime modifiedAt;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;
}
