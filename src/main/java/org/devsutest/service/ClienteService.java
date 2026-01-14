package org.devsutest.service;

import org.devsutest.dto.ClienteDTO;
import org.devsutest.dto.respuestaBase.BaseResponseDTO;
import org.devsutest.dto.respuestaBase.BaseResponseSimpleDTO;

public interface ClienteService {
    BaseResponseDTO crearCliente(ClienteDTO cliente);
    BaseResponseSimpleDTO obtenerCliente(Long idCliente);
    BaseResponseDTO obtenerTodosLosClientes();
    BaseResponseDTO actualizarCliente(ClienteDTO cliente, Long idCliente);
    BaseResponseDTO eliminarCliente(Long idCliente);
}
