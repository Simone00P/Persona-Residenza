package com.ey.personaresidenza.service;

import com.ey.personaresidenza.entity.Persona;
import com.ey.personaresidenza.entity.Residenza;
import com.ey.personaresidenza.repository.ResidenzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResidenzaService {

    @Autowired
    private PersonaService personaService;

    @Autowired
    private ResidenzaRepository residenzaRepository;

    public Residenza addResidenza(Long personaId, Residenza residenza) {
        Persona persona = personaService.getPersonaById(personaId).orElseThrow();
        residenza.setPersona(persona);
        return residenzaRepository.save(residenza);
    }

    public Residenza updateResidenza(Long personaId, Residenza residenza) {
        // Qui si assume che esista un metodo per trovare la residenza associata a personaId
        Residenza esistente = residenzaRepository.findById(personaId).orElseThrow();
        esistente.setIndirizzo(residenza.getIndirizzo());
        esistente.setCap(residenza.getCap());
        esistente.setCitta(residenza.getCitta());
        return residenzaRepository.save(esistente);
    }
}