package arden.java.islab1.model.vehicle;

import arden.java.islab1.model.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "is_lab1_vehicles")
@Data
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name cannot be null or empty")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coordinates_id", referencedColumnName = "id")
    @NotNull(message = "Coordinates cannot be null")
    private Coordinates coordinates;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Date creationDate;

    @Enumerated(EnumType.STRING)
    private VehicleType type;

    @Positive(message = "Engine power must be greater than 0")
    private double enginePower;

    @Positive(message = "Number of wheels must be greater than 0")
    private int numberOfWheels;

    @Positive(message = "Capacity must be greater than 0")
    private Double capacity;

    @Positive(message = "Distance travelled must be greater than 0")
    private Double distanceTravelled;

    @Positive(message = "Fuel consumption must be greater than 0")
    private Double fuelConsumption;

    @NotNull(message = "Fuel type cannot be null")
    @Enumerated(EnumType.STRING)
    private FuelType fuelType;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
    private User user;
}
