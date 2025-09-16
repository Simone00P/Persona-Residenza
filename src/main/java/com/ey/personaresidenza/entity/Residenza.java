package com.ey.personaresidenza.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @NotBlank(message = "'indirizzo' non può essere vuoto")
    private String indirizzo;

    @NotBlank(message = "'cap' non può essere vuoto")
    private String cap;

    @NotBlank(message = "'citta' non può essere vuota")
    private String citta;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_anagrafica", nullable = false,
            foreignKey = @ForeignKey(name = "fk_residenza_persona"))
    @JsonIgnore
    private Persona persona;
}
