package ua.maestr0.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
@Data
@NoArgsConstructor
public class Ticket {
    @Id
    @SequenceGenerator(
            name = "ticket_seq",
            sequenceName = "seq_ticket_id",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ticket_seq"
    )
    private Long id;


    @Column(
            name = "created_at",
            nullable = false,
            updatable = false
    )
    private LocalDateTime createdAt;


    @ManyToOne
    @JoinColumn(
            name = "client_id",
            nullable = false
    )
    private Client client;


    @ManyToOne
    @JoinColumn(
            name = "from_planet_id",
            nullable = false
    )
    private Planet fromPlanet;


    @ManyToOne
    @JoinColumn(
            name = "to_planet_id",
            nullable = false
    )
    private Planet toPlanet;


    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
