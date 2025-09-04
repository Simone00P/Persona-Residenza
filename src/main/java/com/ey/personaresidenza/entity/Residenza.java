package com.ey.personaresidenza.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "residenza")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Residenza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String indirizzo;

    @NotBlank
    private String cap;

    @NotBlank
    private String citta;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_anagrafica", nullable = false,
            foreignKey = @ForeignKey(name = "fk_residenza_persona"))
    private Persona persona;
}
