package org.devsutest.service.impl;

import org.modelmapper.ModelMapper;
import org.devsutest.dto.CuentaDTO;
import org.devsutest.dto.respuestaBase.BaseResponseDTO;
import org.devsutest.dto.respuestaBase.BaseResponseSimpleDTO;
import org.devsutest.dto.respuestaBase.ResponseBaseMapper;
import org.devsutest.exception.ClienteException;
import org.devsutest.exception.CuentaException;
import org.devsutest.model.Cliente;
import org.devsutest.model.Cuenta;
import org.devsutest.repository.ClienteRepository;
import org.devsutest.repository.CuentaRepository;
import org.devsutest.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.stream.Collectors;

@Service
public class CuentaServiceImpl implements CuentaService {
    private final ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private CuentaRepository cuentaRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public BaseResponseDTO crearCuenta(CuentaDTO cuenta) {
        try {
            Cuenta cuentaExiste = cuentaRepository.findByNumeroCuenta(cuenta.getNumeroCuenta()).orElse(null);
            if (cuentaExiste != null) {
                throw new CuentaException(CuentaException.YA_EXISTE_CUENTA);
            }
            return ResponseBaseMapper.generateOkResponseCreateUpdate(cuentaRepository.save(crearCuentaModel(cuenta)).getId());
        } catch (Exception e) {
            return ResponseBaseMapper.generateErrorResponse(e.getMessage());
        }
    }

    @Override
    public BaseResponseSimpleDTO obtenerCuenta(Long idCuenta) {
        return ResponseBaseMapper.generateOkSimpleResponse(modelMapper.map(
                cuentaRepository.findById(idCuenta)
                        .orElseThrow(() -> new EntityNotFoundException("No se encontró la cuenta con id: " + idCuenta)),
                CuentaDTO.class));
    }

    @Override
    public BaseResponseDTO obtenerTodasLasCuentas() {
        return ResponseBaseMapper.generateOkResponse(cuentaRepository.findAll().
                stream().
                map(cuenta -> {
                    CuentaDTO dto = modelMapper.map(cuenta, CuentaDTO.class);
                    dto.setCliente(cuenta.getIdCliente().getNombre());
                    return dto;
                })
                .collect(Collectors.toList()));
    }

    @Override
    public BaseResponseDTO actualizarCuenta(CuentaDTO cuenta, Long idCuenta) {
        try {
            cuentaRepository.findById(idCuenta).orElseThrow(() -> new CuentaException(CuentaException.NO_EXISTE_CUENTA));
            Cuenta cuentaNuevo = crearCuentaModel(cuenta);
            cuentaNuevo.setId(idCuenta);
            return ResponseBaseMapper.generateOkResponseCreateUpdate(cuentaRepository.save(cuentaNuevo).getId());

        } catch (Exception e) {

            return ResponseBaseMapper.generateErrorResponse(e.getMessage());
        }
    }

    @Override
    public BaseResponseDTO eliminarCuenta(Long idCuenta) {
        try {
            Cuenta cuenta = cuentaRepository.findById(idCuenta).orElseThrow(() -> new CuentaException(CuentaException.NO_EXISTE_CUENTA));
            cuentaRepository.delete(cuenta);
            return ResponseBaseMapper.generateOkResponseDelete(cuenta.getId());
        } catch (Exception e) {

            return ResponseBaseMapper.generateErrorResponse(e.getMessage());
        }
    }

    private Cuenta crearCuentaModel(CuentaDTO cuenta
    ) {
        Cliente cliente = clienteRepository.findById(cuenta.getIdCliente()).orElseThrow(() -> new ClienteException(ClienteException.NO_EXISTE_CLIENTE));
        Cuenta nuevaCuenta = new Cuenta();
        nuevaCuenta.setNumeroCuenta(cuenta.getNumeroCuenta());
        nuevaCuenta.setTipo(cuenta.getTipo());
        nuevaCuenta.setEstado(cuenta.getEstado());
        nuevaCuenta.setSaldoInicial(cuenta.getSaldoInicial());
        nuevaCuenta.setIdCliente(cliente);
        return nuevaCuenta;
    }
}
