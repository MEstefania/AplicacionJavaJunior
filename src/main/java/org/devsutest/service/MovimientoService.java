package org.devsutest.service;

import org.devsutest.dto.MovimientoDTO;
import org.devsutest.dto.respuestaBase.BaseResponseDTO;
import org.devsutest.dto.respuestaBase.BaseResponseSimpleDTO;

public interface MovimientoService {
    BaseResponseDTO crearMovimiento(MovimientoDTO Movimiento);
    BaseResponseSimpleDTO obtenerMovimiento(Long idMovimiento);
    BaseResponseDTO obtenerTodosLosMovimientos();
    BaseResponseDTO actualizarMovimiento(MovimientoDTO Movimiento, Long idMovimiento);
    BaseResponseDTO eliminarMovimiento(Long idMovimiento);
}
