package org.sofkajavajunior.service;

import org.sofkajavajunior.dto.MovimientoDTO;
import org.sofkajavajunior.dto.respuestaBase.BaseRespondeDTO;

public interface MovimientoService {
    BaseRespondeDTO crearMovimiento(MovimientoDTO Movimiento);
    BaseRespondeDTO obtenerMovimiento(Long idMovimiento);
    BaseRespondeDTO actualizarMovimiento(MovimientoDTO Movimiento, Long idMovimiento);
    BaseRespondeDTO eliminarMovimiento(Long idMovimiento);
}
