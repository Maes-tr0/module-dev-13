package ua.maestr0.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "clients")
@ToString(exclude = "tickets")
@Data
@NoArgsConstructor
public class Client {
    @Id
    @SequenceGenerator(
            name = "client_seq",
            sequenceName = "seq_client_id",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "client_seq"
    )
    private Long id;


    @Column(
            nullable = false,
            length = 200
    )
    @Size(
            min = 3,
            max = 200,
            message = "Name must be between 3 and 200 characters"
    )
    private String name;


    @OneToMany(
            mappedBy = "client",
            cascade = CascadeType.ALL
    )
    private List<Ticket> tickets;
}
