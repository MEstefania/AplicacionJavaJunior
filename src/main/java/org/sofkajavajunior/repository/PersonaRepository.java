package org.sofkajavajunior.repository;

import org.sofkajavajunior.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository  extends JpaRepository<Persona, Long> {
}
