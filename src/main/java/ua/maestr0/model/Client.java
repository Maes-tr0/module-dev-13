package ua.maestr0.model;

import jakarta.persistence.*;
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
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Ticket> tickets;
}
