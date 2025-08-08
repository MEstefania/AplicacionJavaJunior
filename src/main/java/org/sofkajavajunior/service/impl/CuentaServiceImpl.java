package org.sofkajavajunior.service.impl;

import org.sofkajavajunior.dto.CuentaDTO;
import org.sofkajavajunior.dto.respuestaBase.BaseResponseDTO;
import org.sofkajavajunior.service.CuentaService;
import org.springframework.stereotype.Service;

@Service
public class CuentaServiceImpl implements CuentaService {
    @Override
    public BaseResponseDTO crearCuenta(CuentaDTO Cuenta) {
        return null;
    }

    @Override
    public BaseResponseDTO obtenerCuenta(Long idCuenta) {
        return null;
    }

    @Override
    public BaseResponseDTO obtenerCuenta(String nombreCuenta) {
        return null;
    }

    @Override
    public BaseResponseDTO actualizarCuenta(CuentaDTO Cuenta, Long idCuenta) {
        return null;
    }

    @Override
    public BaseResponseDTO eliminarCuenta(Long idCuenta) {
        return null;
    }
}
