package com.ey.personaresidenza.controller;

import com.ey.personaresidenza.entity.Residenza;
import com.ey.personaresidenza.service.ResidenzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/persone/{personaId}/residenza")
public class PersonaResidenzaController 
{

    @Autowired
    private ResidenzaService residenzaService;

    @PostMapping
    public Residenza addResidenza(@PathVariable Long personaId, @RequestBody Residenza residenza) 
    {
        return residenzaService.addResidenza(personaId, residenza);
    }

    @PutMapping
    public Residenza updateResidenza(@PathVariable Long personaId, @RequestBody Residenza residenza) 
    {
        return residenzaService.updateResidenza(personaId, residenza);
    }
}