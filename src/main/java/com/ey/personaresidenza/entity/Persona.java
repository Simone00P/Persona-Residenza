package com.ey.personaresidenza.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "persona")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Persona 
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "'nome' non può essere vuoto")
    private String nome;

    @NotBlank(message = "'cognome' non può essere vuoto")
    private String cognome;

    @Column(name = "codice_fiscale", nullable = false, unique = true, length = 16)
    @NotBlank(message = "'codice_fiscale' non può essere vuoto")
    private String codiceFiscale;

    @Past(message = "'dataNascita' deve essere una data passata")
    private LocalDate dataNascita;

    @OneToOne(mappedBy = "persona", cascade = CascadeType.ALL, orphanRemoval = true)
    private Residenza residenza;
}
