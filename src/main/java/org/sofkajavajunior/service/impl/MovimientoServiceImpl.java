package org.sofkajavajunior.service.impl;

import org.modelmapper.ModelMapper;
import org.sofkajavajunior.dto.MovimientoDTO;
import org.sofkajavajunior.dto.respuestaBase.BaseResponseDTO;
import org.sofkajavajunior.dto.respuestaBase.BaseResponseSimpleDTO;
import org.sofkajavajunior.dto.respuestaBase.ResponseBaseMapper;
import org.sofkajavajunior.exception.MovimientoException;
import org.sofkajavajunior.model.Movimiento;
import org.sofkajavajunior.repository.MovimientoRepository;
import org.sofkajavajunior.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.stream.Collectors;

@Service
public class MovimientoServiceImpl implements MovimientoService {
    private final ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private MovimientoRepository movimientoRepository;

    @Override
    public BaseResponseDTO crearMovimiento(MovimientoDTO movimiento) {
        try {

            BigDecimal saldo = calcularSaldoActualizado(movimiento.getIdCuenta());
            return ResponseBaseMapper.generateOkResponseCreateUpdate(movimientoRepository.save(crearMovimientoModel(movimiento, saldo)).getId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseBaseMapper.generateErrorResponse(e.getMessage());
        }
    }

    private BigDecimal calcularSaldoActualizado(Long idcuenta){
        return BigDecimal.ZERO;
    }

    @Override
    public BaseResponseSimpleDTO obtenerMovimiento(Long idMovimiento) {
        return ResponseBaseMapper.generateOkSimpleResponse(modelMapper.map(
                movimientoRepository.findById(idMovimiento),
                MovimientoDTO.class));
    }

    @Override
    public BaseResponseDTO obtenerTodosLosMovimientos() {
        return ResponseBaseMapper.generateOkResponse(movimientoRepository.findAll().
                stream().
                map(movimiento -> modelMapper.map(movimiento, MovimientoDTO.class))
                .collect(Collectors.toList()));
    }

    @Override
    public BaseResponseDTO actualizarMovimiento(MovimientoDTO movimiento, Long idMovimiento) {
        try {
            movimientoRepository.findById(idMovimiento).orElseThrow(() -> new MovimientoException(MovimientoException.NO_EXISTE_MOVIMIENTO));
            Movimiento movimientoNuevo = crearMovimientoModel(movimiento, BigDecimal.ZERO);
            movimientoNuevo.setId(idMovimiento);
            return ResponseBaseMapper.generateOkResponseCreateUpdate(movimientoRepository.save(movimientoNuevo).getId());

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseBaseMapper.generateErrorResponse(e.getMessage());
        }
    }

    @Override
    public BaseResponseDTO eliminarMovimiento(Long idMovimiento) {
        try {
            Movimiento movimiento = movimientoRepository.findById(idMovimiento).orElseThrow(() -> new MovimientoException(MovimientoException.NO_EXISTE_MOVIMIENTO));
            movimientoRepository.delete(movimiento);
            return ResponseBaseMapper.generateOkResponseDelete(movimiento.getId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseBaseMapper.generateErrorResponse(e.getMessage());
        }
    }
    private Movimiento crearMovimientoModel(MovimientoDTO movimiento, BigDecimal saldo
    ) {
        Movimiento nuevoMovimiento = new Movimiento();
        nuevoMovimiento.setFecha(movimiento.getFecha());
        nuevoMovimiento.setTipo(movimiento.getTipo());
        nuevoMovimiento.setValorMovimiento(movimiento.getValorMovimiento());
        nuevoMovimiento.setIdCuenta(movimiento.getIdCuenta());
        nuevoMovimiento.setSaldoMovimiento(saldo);
        return nuevoMovimiento;
    }
}

