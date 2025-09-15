package com.ey.personaresidenza.controller;

import com.ey.personaresidenza.entity.Persona;
import com.ey.personaresidenza.entity.Residenza;
import com.ey.personaresidenza.service.PersonaService;
import com.ey.personaresidenza.service.ResidenzaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/persone")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @Autowired
    private ResidenzaService residenzaService;

    @GetMapping
    public List<Persona> getAllPersone() {
        return personaService.getAllPersone();
    }

    @GetMapping("/{id}")
    public Optional<Persona> getPersonaById(@PathVariable Long id) {
        return personaService.getPersonaById(id);
    }

    @GetMapping("/per-indirizzo")
    public List<Persona> getPersoneByIndirizzo(@RequestParam String indirizzo) {
        return personaService.getPersoneByIndirizzo(indirizzo);
    }

    @PostMapping
    public Persona createPersona(@RequestBody Persona persona) {
        return personaService.createPersona(persona);
    }

    @PutMapping("/{id}")
    public Persona updatePersona(@PathVariable Long id, @RequestBody Persona personaDettagli) {
        return personaService.updatePersona(id, personaDettagli);
    }

    @DeleteMapping("/{id}")
    public void deletePersona(@PathVariable Long id) {
        personaService.deletePersona(id);
    }

    @PostMapping("/{personaId}/residenza")
    public Residenza addResidenza(@PathVariable Long personaId, @RequestBody Residenza residenza) {
        return residenzaService.addResidenza(personaId, residenza);
    }

    @PutMapping("/{personaId}/residenza")
    public Residenza updateResidenza(@PathVariable Long personaId, @RequestBody Residenza residenza) {
        return residenzaService.updateResidenza(personaId, residenza);
    }

    
}