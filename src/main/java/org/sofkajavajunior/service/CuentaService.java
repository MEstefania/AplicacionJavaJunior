package org.sofkajavajunior.service;

import org.sofkajavajunior.dto.CuentaDTO;
import org.sofkajavajunior.dto.respuestaBase.BaseResponseDTO;
import org.sofkajavajunior.dto.respuestaBase.BaseResponseSimpleDTO;

public interface CuentaService {
    BaseResponseDTO crearCuenta(CuentaDTO Cuenta);
    BaseResponseSimpleDTO obtenerCuenta(Long idCuenta);
    BaseResponseDTO obtenerTodasLasCuentas();
    BaseResponseDTO actualizarCuenta(CuentaDTO Cuenta, Long idCuenta);
    BaseResponseDTO eliminarCuenta(Long idCuenta);
}
