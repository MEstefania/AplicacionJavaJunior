package org.sofkajavajunior.service.impl;

import org.sofkajavajunior.dto.ClienteDTO;
import org.sofkajavajunior.dto.respuestaBase.BaseRespondeDTO;
import org.sofkajavajunior.service.ClienteService;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService {
    @Override
    public BaseRespondeDTO crearCliente(ClienteDTO cliente) {
        return null;
    }

    @Override
    public BaseRespondeDTO obtenerCliente(Long idCliente) {
        return null;
    }

    @Override
    public BaseRespondeDTO obtenerTodosLosClientes() {
        return null;
    }

    @Override
    public BaseRespondeDTO actualizarCliente(ClienteDTO cliente, Long idCliente) {
        return null;
    }

    @Override
    public BaseRespondeDTO eliminarCliente(Long idCliente) {
        return null;
    }
}
