package org.sofkajavajunior.service;

import org.sofkajavajunior.dto.CuentaDTO;
import org.sofkajavajunior.dto.respuestaBase.BaseRespondeDTO;

public interface CuentaService {
    BaseRespondeDTO crearCuenta(CuentaDTO Cuenta);
    BaseRespondeDTO obtenerCuenta(Long idCuenta);
    BaseRespondeDTO obtenerCuenta(String nombreCuenta);
    BaseRespondeDTO actualizarCuenta(CuentaDTO Cuenta, Long idCuenta);
    BaseRespondeDTO eliminarCuenta(Long idCuenta);
}
