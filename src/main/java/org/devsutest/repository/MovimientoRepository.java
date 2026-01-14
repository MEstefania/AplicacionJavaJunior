package org.devsutest.repository;

import org.devsutest.model.Cuenta;
import org.devsutest.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
    Optional<Movimiento> findFirstByIdCuentaOrderByFechaDesc(final Cuenta IdCuenta);
    Optional<List<Movimiento>> findByIdCuentaAndFechaAfter(final Cuenta IdCuenta, final Timestamp Fecha);
    Optional<List<Movimiento>> findByIdCuentaAndFechaBetweenOrderByFechaDesc(final Cuenta IdCuenta, Timestamp desde, Timestamp hasta);
    Optional<Movimiento> findFirstsByIdCuentaAndFechaBeforeOrderByFechaDesc(final Cuenta IdCuenta, final Timestamp Fecha);
}
