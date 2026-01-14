package org.devsutest.service;

import org.devsutest.dto.CuentaDTO;
import org.devsutest.dto.respuestaBase.BaseResponseDTO;
import org.devsutest.dto.respuestaBase.BaseResponseSimpleDTO;

public interface CuentaService {
    BaseResponseDTO crearCuenta(CuentaDTO Cuenta);
    BaseResponseSimpleDTO obtenerCuenta(Long idCuenta);
    BaseResponseDTO obtenerTodasLasCuentas();
    BaseResponseDTO actualizarCuenta(CuentaDTO Cuenta, Long idCuenta);
    BaseResponseDTO eliminarCuenta(Long idCuenta);
}
