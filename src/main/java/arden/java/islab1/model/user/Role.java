package arden.java.islab1.model.user;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "is_lab1_roles")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @Column(unique = true, nullable = false)
    private String role;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();
}
