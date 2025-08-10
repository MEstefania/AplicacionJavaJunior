package org.sofkajavajunior.service;

import org.sofkajavajunior.dto.respuestaBase.BaseResponseDTO;

import java.util.Date;

public interface ReporteService {
    BaseResponseDTO obtenerTodasLasCuentasSegunClienteYFecha(Date fechaInicio, Date fechaFin, Long clienteId);
}
