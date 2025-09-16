package com.ey.personaresidenza.service;

import com.ey.personaresidenza.entity.Persona;
import com.ey.personaresidenza.entity.Residenza;
import com.ey.personaresidenza.exception.ResourceNotFoundException;
import com.ey.personaresidenza.repository.ResidenzaRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResidenzaService {

    @Autowired
    private PersonaService personaService;

    @Autowired
    private ResidenzaRepository residenzaRepository;

    @Transactional
    public Residenza addResidenza(Long personaId, Residenza residenza) {
        Persona persona = personaService.getPersonaById(personaId).orElseThrow();
        residenza.setPersona(persona);
        return residenzaRepository.save(residenza);
    }

    @Transactional
    public Residenza updateResidenza(Long personaId, Residenza residenza) {
        Persona persona = personaService.getPersonaById(personaId)
            .orElseThrow(() -> new ResourceNotFoundException("Persona non trovata con ID: " + personaId));

        Residenza formerResidenza = persona.getResidenza();
        if (formerResidenza == null) {
            throw new ResourceNotFoundException("Nessuna residenza associata alla persona con ID: " + personaId);
        }

        formerResidenza.setIndirizzo(residenza.getIndirizzo());
        formerResidenza.setCap(residenza.getCap());
        formerResidenza.setCitta(residenza.getCitta());

        return residenzaRepository.save(formerResidenza);
    }
}