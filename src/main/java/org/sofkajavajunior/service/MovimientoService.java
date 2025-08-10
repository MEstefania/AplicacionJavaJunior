package org.sofkajavajunior.service;

import org.sofkajavajunior.dto.MovimientoDTO;
import org.sofkajavajunior.dto.respuestaBase.BaseResponseDTO;
import org.sofkajavajunior.dto.respuestaBase.BaseResponseSimpleDTO;

public interface MovimientoService {
    BaseResponseDTO crearMovimiento(MovimientoDTO Movimiento);
    BaseResponseSimpleDTO obtenerMovimiento(Long idMovimiento);
    BaseResponseDTO obtenerTodosLosMovimientos();
    BaseResponseDTO actualizarMovimiento(MovimientoDTO Movimiento, Long idMovimiento);
    BaseResponseDTO eliminarMovimiento(Long idMovimiento);
}
