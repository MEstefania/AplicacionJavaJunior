package org.sofkajavajunior.service.impl;

import org.sofkajavajunior.dto.MovimientoDTO;
import org.sofkajavajunior.dto.MovimientoPorCuentaDTO;
import org.sofkajavajunior.dto.respuestaBase.BaseResponseDTO;
import org.sofkajavajunior.dto.respuestaBase.ResponseBaseMapper;
import org.sofkajavajunior.exception.ClienteException;
import org.sofkajavajunior.exception.CuentaException;
import org.sofkajavajunior.model.Cliente;
import org.sofkajavajunior.model.Cuenta;
import org.sofkajavajunior.model.Movimiento;
import org.sofkajavajunior.repository.ClienteRepository;
import org.sofkajavajunior.repository.CuentaRepository;
import org.sofkajavajunior.repository.MovimientoRepository;
import org.sofkajavajunior.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReporteServiceImpl implements ReporteService {
    @Autowired
    private CuentaRepository cuentaRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private MovimientoRepository movimientoRepository;

    @Override
    public BaseResponseDTO obtenerTodasLasCuentasSegunClienteYFecha(Date fechaInicio, Date fechaFin, Long clienteId) {
        //Buscar cuentas del cliente
        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(() -> new ClienteException(ClienteException.NO_EXISTE_CLIENTE));
        List<Cuenta> cuentas = cuentaRepository.findByIdCliente(cliente).orElseThrow(() -> new CuentaException(CuentaException.NO_TIENE_CUENTA));

        List<MovimientoPorCuentaDTO> movimientosPorCuenta = new ArrayList<>();
        for (Cuenta cuenta : cuentas) {
            MovimientoPorCuentaDTO cuentaDetallada = new MovimientoPorCuentaDTO();
            cuentaDetallada.setCuenta(cuenta);
            List<Movimiento> movimientos = movimientoRepository.findByIdCuentaAndFechaBetweenOrderByFechaDesc(cuenta, new Timestamp(fechaInicio.getTime()), new Timestamp(fechaFin.getTime())).orElse(null);
            List<MovimientoDTO> movimientoDTOS = new ArrayList<>();
            if (movimientos != null && !movimientos.isEmpty()) {
                cuentaDetallada.setSaldo(movimientos.get(0).getSaldoMovimiento());
                for (Movimiento mov : movimientos) {
                    MovimientoDTO movimientoDTO = new MovimientoDTO(mov.getId(), mov.getFecha(), mov.getTipo(), mov.getValorMovimiento(), mov.getSaldoMovimiento(), mov.getIdCuenta().getId());
                    movimientoDTOS.add(movimientoDTO);
                }
            }
            cuentaDetallada.setMovimientos(movimientoDTOS);
            movimientosPorCuenta.add(cuentaDetallada);
        }
        return ResponseBaseMapper.generateOkResponse(new ArrayList<>(movimientosPorCuenta));
    }

}
