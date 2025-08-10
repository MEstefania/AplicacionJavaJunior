package org.sofkajavajunior.repository;

import org.sofkajavajunior.model.Cuenta;
import org.sofkajavajunior.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
    Optional<Movimiento> findFirstByIdCuentaOrderByFechaDesc(final Cuenta IdCuenta);
    Optional<List<Movimiento>> findByIdCuentaAndFechaAfter(final Cuenta IdCuenta, final Timestamp Fecha);
    Optional<Movimiento> findFirstsByIdCuentaAndFechaBeforeOrderByFechaDesc(final Cuenta IdCuenta, final Timestamp Fecha);
}
