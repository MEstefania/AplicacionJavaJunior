package org.sofkajavajunior.service;

import org.sofkajavajunior.dto.ClienteDTO;
import org.sofkajavajunior.dto.respuestaBase.BaseResponseDTO;
import org.sofkajavajunior.dto.respuestaBase.BaseResponseSimpleDTO;

public interface ClienteService {
    BaseResponseDTO crearCliente(ClienteDTO cliente);
    BaseResponseSimpleDTO obtenerCliente(Long idCliente);
    BaseResponseDTO obtenerTodosLosClientes();
    BaseResponseDTO actualizarCliente(ClienteDTO cliente, Long idCliente);
    BaseResponseDTO eliminarCliente(Long idCliente);
}
