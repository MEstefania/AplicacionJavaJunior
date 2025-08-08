package org.sofkajavajunior.service;

import org.sofkajavajunior.dto.CuentaDTO;
import org.sofkajavajunior.dto.respuestaBase.BaseResponseDTO;

public interface CuentaService {
    BaseResponseDTO crearCuenta(CuentaDTO Cuenta);
    BaseResponseDTO obtenerCuenta(Long idCuenta);
    BaseResponseDTO obtenerCuenta(String nombreCuenta);
    BaseResponseDTO actualizarCuenta(CuentaDTO Cuenta, Long idCuenta);
    BaseResponseDTO eliminarCuenta(Long idCuenta);
}
