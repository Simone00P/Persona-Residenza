package com.ey.personaresidenza.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PersonaValidationTests {

    @Autowired
    private Validator validator;

    @Test
    public void testValidPersona() {
        Persona persona = Persona.builder()
                .nome("Mario")
                .cognome("Rossi")
                .codiceFiscale("RSSMRA85M01H501Z")
                .dataNascita(LocalDate.of(1985, 1, 1))
                .build();

        Set<ConstraintViolation<Persona>> violations = validator.validate(persona);
        assertTrue(violations.isEmpty(), "La persona valida non dovrebbe avere violazioni");
    }

    @Test
    public void testPersonaNomeNull() {
        Persona persona = Persona.builder()
                .cognome("Rossi")
                .codiceFiscale("RSSMRA85M01H501Z")
                .dataNascita(LocalDate.of(1985, 1, 1))
                .build();

        Set<ConstraintViolation<Persona>> violations = validator.validate(persona);
        assertFalse(violations.isEmpty(), "La persona con nome nullo dovrebbe avere violazioni");
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("nome")), "La violazione dovrebbe riguardare il campo 'nome'");
    }

    @Test
    public void testPersonaCodiceFiscaleBlank() {
        Persona persona = Persona.builder()
                .nome("Mario")
                .cognome("Rossi")
                .codiceFiscale("")
                .dataNascita(LocalDate.of(1985, 1, 1))
                .build();

        Set<ConstraintViolation<Persona>> violations = validator.validate(persona);
        assertFalse(violations.isEmpty(), "La persona con codice fiscale vuoto dovrebbe avere violazioni");
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("codiceFiscale")), "La violazione dovrebbe riguardare il campo 'codiceFiscale'");
    }

    @Test
    public void testPersonaDataNascitaFutura() {
        Persona persona = Persona.builder()
                .nome("Mario")
                .cognome("Rossi")
                .codiceFiscale("RSSMRA85M01H501Z")
                .dataNascita(LocalDate.now().plusDays(1)) // Data futura
                .build();

        Set<ConstraintViolation<Persona>> violations = validator.validate(persona);
        assertFalse(violations.isEmpty(), "La persona con data di nascita futura dovrebbe avere violazioni");
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("dataNascita")), "La violazione dovrebbe riguardare il campo 'dataNascita'");
    }
}