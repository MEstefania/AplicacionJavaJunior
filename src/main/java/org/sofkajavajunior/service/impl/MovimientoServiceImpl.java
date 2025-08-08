package org.sofkajavajunior.service.impl;

import org.sofkajavajunior.dto.MovimientoDTO;
import org.sofkajavajunior.dto.respuestaBase.BaseRespondeDTO;
import org.sofkajavajunior.service.MovimientoService;
import org.springframework.stereotype.Service;

@Service
public class MovimientoServiceImpl implements MovimientoService {
    @Override
    public BaseRespondeDTO crearMovimiento(MovimientoDTO Movimiento) {
        return null;
    }

    @Override
    public BaseRespondeDTO obtenerMovimiento(Long idMovimiento) {
        return null;
    }

    @Override
    public BaseRespondeDTO actualizarMovimiento(MovimientoDTO Movimiento, Long idMovimiento) {
        return null;
    }

    @Override
    public BaseRespondeDTO eliminarMovimiento(Long idMovimiento) {
        return null;
    }
}
