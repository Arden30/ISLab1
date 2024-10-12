package arden.java.islab1.model.vehicle;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Entity
@Table(name = "is_lab1_coordinates")
@Data
public class Coordinates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = -118, message = "X must be greater than -118")
    private double x;

    @Min(value = -652, message = "Y must be greater than -652")
    private long y;
}

