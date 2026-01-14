package org.devsutest.repository;

import org.devsutest.model.Cliente;
import org.devsutest.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    Optional<Cuenta> findByNumeroCuenta(final String numeroCuenta);

    Optional<List<Cuenta>> findByIdCliente(final Cliente cliente);
}
