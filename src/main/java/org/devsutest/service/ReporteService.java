package org.devsutest.service;

import org.devsutest.dto.respuestaBase.BaseResponseDTO;

import java.util.Date;

public interface ReporteService {
    BaseResponseDTO obtenerTodasLasCuentasSegunClienteYFecha(Date fechaInicio, Date fechaFin, Long clienteId);
}
