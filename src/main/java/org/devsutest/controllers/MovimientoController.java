package org.devsutest.controllers;

import org.devsutest.dto.MovimientoDTO;
import org.devsutest.dto.respuestaBase.BaseResponseDTO;
import org.devsutest.dto.respuestaBase.BaseResponseSimpleDTO;
import org.devsutest.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {
    @Autowired
    private MovimientoService movimientoService;
    
    @PostMapping
    public ResponseEntity<BaseResponseDTO> crearMovimiento(
            @Valid @RequestBody MovimientoDTO nuevo_movimiento
    ) {
        return new ResponseEntity<>(movimientoService.crearMovimiento(nuevo_movimiento), HttpStatus.OK);
    }
    @GetMapping("/{movimiento_id}")
    public ResponseEntity<BaseResponseSimpleDTO> obtenerMovimiento(
            @Valid @PathVariable("movimiento_id") Long movimientoId
    ){
        return new ResponseEntity<>(movimientoService.obtenerMovimiento(movimientoId), HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<BaseResponseDTO> obtenerTodosLosMovimientos(){
        return new ResponseEntity<>(movimientoService.obtenerTodosLosMovimientos(), HttpStatus.OK);
    }

    @PutMapping("/{movimiento_id}")
    public ResponseEntity<BaseResponseDTO> actualizarMovimiento(
            @Valid @PathVariable("movimiento_id") Long movimientoId,
            @Valid @RequestBody MovimientoDTO  nuevo_movimiento
    ) {
        return new ResponseEntity<>(movimientoService.actualizarMovimiento(nuevo_movimiento, movimientoId), HttpStatus.OK);
    }

    @DeleteMapping("/{movimiento_id}")
    public ResponseEntity<BaseResponseDTO> eliminarMovimiento(
            @Valid @PathVariable("movimiento_id") Long movimientoId
    ){
        return new ResponseEntity<>(movimientoService.eliminarMovimiento(movimientoId), HttpStatus.OK);
    }
}
