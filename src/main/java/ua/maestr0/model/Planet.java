package ua.maestr0.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "planets")
@Data
@NoArgsConstructor
public class Planet {
    @Id
    @Column(length = 50)
    @Pattern(
            regexp = "^[A-Z0-9]+$",
            message = "ID must contain only uppercase Latin letters and digits"
    )
    private String id;


    @Column(
            nullable = false,
            length = 500
    )
    @Size(
            min = 1,
            max = 500,
            message = "Name must be between 1 and 500 characters"
    )
    private String name;
}
