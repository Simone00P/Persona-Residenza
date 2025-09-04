package com.ey.personaresidenza.repository;

import com.ey.personaresidenza.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
    @Query("select distinct p from Persona p join p.residenze r where lower(r.indirizzo) = lower(:indirizzo)")
    List<Persona> findByIndirizzo(@Param("indirizzo") String indirizzo);
}
