package org.sofkajavajunior.repository;

import org.sofkajavajunior.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
    Optional<Movimiento> findFirstByIdCuentaOrderByFechaDesc(final Long IdCuenta);
}
