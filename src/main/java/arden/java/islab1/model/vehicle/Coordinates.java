package arden.java.islab1.model.vehicle;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "is_lab1_coordinates")
@Getter
@Setter
public class Coordinates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = -118, message = "X must be greater than -118")
    private double x;

    @Min(value = -652, message = "Y must be greater than -652")
    private double y;
}

