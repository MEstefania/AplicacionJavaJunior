package org.sofkajavajunior.service.impl;

import org.sofkajavajunior.dto.ClienteDTO;
import org.sofkajavajunior.dto.respuestaBase.BaseResponseDTO;
import org.sofkajavajunior.dto.respuestaBase.BaseResponseSimpleDTO;
import org.sofkajavajunior.dto.respuestaBase.ResponseBaseMapper;
import org.sofkajavajunior.exception.ClienteException;
import org.sofkajavajunior.model.Cliente;
import org.sofkajavajunior.repository.ClienteRepository;
import org.sofkajavajunior.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import javax.persistence.EntityNotFoundException;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService {
    private final ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public BaseResponseDTO crearCliente(ClienteDTO cliente) {
        try {
            Cliente clienteExiste = clienteRepository.findByIdentificacion(cliente.getIdentificacion()).orElse(null);
            if (clienteExiste != null) {
                throw new ClienteException(ClienteException.YA_EXISTE_CLIENTE);
            }
            return ResponseBaseMapper.generateOkResponseCreateUpdate(clienteRepository.save(crearClienteModel(cliente)).getId());
        } catch (Exception e) {
            return ResponseBaseMapper.generateErrorResponse(e.getMessage());
        }
    }

    @Override
    public BaseResponseSimpleDTO obtenerCliente(Long idCliente) {
            return ResponseBaseMapper.generateOkSimpleResponse(modelMapper.map(clienteRepository.findById(idCliente)
                            .orElseThrow(() -> new EntityNotFoundException("No se encontró el cliente con id: " + idCliente)),
                    ClienteDTO.class));
    }

    @Override
    public BaseResponseDTO obtenerTodosLosClientes() {
        return ResponseBaseMapper.generateOkResponse(clienteRepository.findAll().
                stream().
                map(cliente -> modelMapper.map(cliente, ClienteDTO.class))
                .collect(Collectors.toList()));
    }

    @Override
    public BaseResponseDTO actualizarCliente(ClienteDTO cliente, Long idCliente) {
        try {
            clienteRepository.findById(idCliente).orElseThrow(() -> new ClienteException(ClienteException.NO_EXISTE_CLIENTE));
            Cliente clienteNuevo = crearClienteModel(cliente);
            clienteNuevo.setId(idCliente);
            return ResponseBaseMapper.generateOkResponseCreateUpdate(clienteRepository.save(clienteNuevo).getId());

        } catch (Exception e) {
            return ResponseBaseMapper.generateErrorResponse(e.getMessage());
        }
    }

    @Override
    public BaseResponseDTO eliminarCliente(Long idCliente) {
        try {
            Cliente cliente = clienteRepository.findById(idCliente).orElseThrow(() -> new ClienteException(ClienteException.NO_EXISTE_CLIENTE));
            clienteRepository.delete(cliente);
            return ResponseBaseMapper.generateOkResponseDelete(cliente.getId());
        } catch (Exception e) {
            return ResponseBaseMapper.generateErrorResponse(e.getMessage());
        }
    }

    private Cliente crearClienteModel(ClienteDTO cliente
    ) {
        Cliente nuevoCliente = new Cliente();
        nuevoCliente.setNombre(cliente.getNombre());
        nuevoCliente.setIdentificacion(cliente.getIdentificacion());
        nuevoCliente.setEstado(cliente.getEstado());
        nuevoCliente.setEdad(cliente.getEdad());
        nuevoCliente.setContrasenia(cliente.getContrasenia());
        nuevoCliente.setDireccion(cliente.getDireccion());
        nuevoCliente.setGenero(cliente.getGenero());
        nuevoCliente.setTelefono(cliente.getTelefono());
        return nuevoCliente;
    }
}
