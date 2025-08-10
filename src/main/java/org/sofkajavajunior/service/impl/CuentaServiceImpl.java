package org.sofkajavajunior.service.impl;

import org.modelmapper.ModelMapper;
import org.sofkajavajunior.dto.CuentaDTO;
import org.sofkajavajunior.dto.respuestaBase.BaseResponseDTO;
import org.sofkajavajunior.dto.respuestaBase.BaseResponseSimpleDTO;
import org.sofkajavajunior.dto.respuestaBase.ResponseBaseMapper;
import org.sofkajavajunior.exception.CuentaException;
import org.sofkajavajunior.model.Cuenta;
import org.sofkajavajunior.repository.CuentaRepository;
import org.sofkajavajunior.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CuentaServiceImpl implements CuentaService {
    private final ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private CuentaRepository cuentaRepository;

    @Override
    public BaseResponseDTO crearCuenta(CuentaDTO cuenta) {
        try {
            Cuenta cuentaExiste = cuentaRepository.findByNumeroCuenta(cuenta.getNumeroCuenta()).orElse(null);
            if (cuentaExiste != null) {
                throw new CuentaException(CuentaException.YA_EXISTE_CUENTA);
            }
            return ResponseBaseMapper.generateOkResponseCreateUpdate(cuentaRepository.save(crearCuentaModel(cuenta)).getId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseBaseMapper.generateErrorResponse(e.getMessage());
        }
    }

    @Override
    public BaseResponseSimpleDTO obtenerCuenta(Long idCuenta) {
        return ResponseBaseMapper.generateOkSimpleResponse(modelMapper.map(
                cuentaRepository.findById(idCuenta),
                CuentaDTO.class));
    }

    @Override
    public BaseResponseDTO obtenerTodasLasCuentas() {
        return ResponseBaseMapper.generateOkResponse(cuentaRepository.findAll().
                stream().
                map(cuenta -> modelMapper.map(cuenta, CuentaDTO.class))
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
            System.out.println(e.getMessage());
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
            System.out.println(e.getMessage());
            return ResponseBaseMapper.generateErrorResponse(e.getMessage());
        }
    }

    private Cuenta crearCuentaModel(CuentaDTO cuenta
    ) {
        Cuenta nuevaCuenta = new Cuenta();
        nuevaCuenta.setNumeroCuenta(cuenta.getNumeroCuenta());
        nuevaCuenta.setTipo(cuenta.getTipo());
        nuevaCuenta.setEstado(cuenta.getEstado());
        nuevaCuenta.setSaldoInicial(cuenta.getSaldoInicial());
        nuevaCuenta.setIdCliente(cuenta.getIdCliente());
        return nuevaCuenta;
    }
}
