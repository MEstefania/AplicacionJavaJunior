package org.sofkajavajunior.service;

import org.sofkajavajunior.dto.MovimientoDTO;
import org.sofkajavajunior.dto.respuestaBase.BaseResponseDTO;

public interface MovimientoService {
    BaseResponseDTO crearMovimiento(MovimientoDTO Movimiento);
    BaseResponseDTO obtenerMovimiento(Long idMovimiento);
    BaseResponseDTO actualizarMovimiento(MovimientoDTO Movimiento, Long idMovimiento);
    BaseResponseDTO eliminarMovimiento(Long idMovimiento);
}
