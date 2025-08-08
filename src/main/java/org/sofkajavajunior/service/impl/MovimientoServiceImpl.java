package org.sofkajavajunior.service.impl;

import org.sofkajavajunior.dto.MovimientoDTO;
import org.sofkajavajunior.dto.respuestaBase.BaseResponseDTO;
import org.sofkajavajunior.service.MovimientoService;
import org.springframework.stereotype.Service;

@Service
public class MovimientoServiceImpl implements MovimientoService {
    @Override
    public BaseResponseDTO crearMovimiento(MovimientoDTO Movimiento) {
        return null;
    }

    @Override
    public BaseResponseDTO obtenerMovimiento(Long idMovimiento) {
        return null;
    }

    @Override
    public BaseResponseDTO actualizarMovimiento(MovimientoDTO Movimiento, Long idMovimiento) {
        return null;
    }

    @Override
    public BaseResponseDTO eliminarMovimiento(Long idMovimiento) {
        return null;
    }
}
