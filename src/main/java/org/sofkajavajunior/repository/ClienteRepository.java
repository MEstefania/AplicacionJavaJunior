package org.sofkajavajunior.repository;

import org.sofkajavajunior.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {
}
