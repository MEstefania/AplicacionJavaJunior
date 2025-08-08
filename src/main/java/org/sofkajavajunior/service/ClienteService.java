package org.sofkajavajunior.service;

import org.sofkajavajunior.dto.ClienteDTO;
import org.sofkajavajunior.dto.respuestaBase.BaseRespondeDTO;

public interface ClienteService {
    BaseRespondeDTO crearCliente(ClienteDTO cliente);
    BaseRespondeDTO obtenerCliente(Long idCliente);
    BaseRespondeDTO obtenerCliente(String nombreCliente);
    BaseRespondeDTO actualizarCliente(ClienteDTO cliente, Long idCliente);
    BaseRespondeDTO eliminarCliente(Long idCliente);
}
