package com.ey.personaresidenza.service;

import com.ey.personaresidenza.entity.Persona;
import com.ey.personaresidenza.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    public List<Persona> getAllPersone() {
        return personaRepository.findAll();
    }

    public Optional<Persona> getPersonaById(Long id) {
        return personaRepository.findById(id);
    }

    public List<Persona> getPersoneByIndirizzo(String indirizzo) {
        return personaRepository.findByIndirizzo(indirizzo);
    }

    public Persona createPersona(Persona persona) {
        return personaRepository.save(persona);
    }

    public Persona updatePersona(Long id, Persona personaDetails) {
        Persona persona = personaRepository.findById(id).orElseThrow();
        persona.setNome(personaDetails.getNome());
        persona.setCognome(personaDetails.getCognome());
        persona.setCodiceFiscale(personaDetails.getCodiceFiscale());
        persona.setDataNascita(personaDetails.getDataNascita());
        return personaRepository.save(persona);
    }

    public void deletePersona(Long id) {
        personaRepository.deleteById(id);
    }

}