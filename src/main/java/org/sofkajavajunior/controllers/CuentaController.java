package org.sofkajavajunior.controllers;

import org.sofkajavajunior.dto.CuentaDTO;
import org.sofkajavajunior.dto.respuestaBase.BaseResponseDTO;
import org.sofkajavajunior.dto.respuestaBase.BaseResponseSimpleDTO;
import org.sofkajavajunior.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {
    @Autowired
    private CuentaService cuentaService;

    @PostMapping
    public ResponseEntity<BaseResponseDTO> crearCuenta(
            @Valid @RequestBody CuentaDTO nueva_cuenta
    ) {
        return new ResponseEntity<>(cuentaService.crearCuenta(nueva_cuenta), HttpStatus.OK);
    }
    @GetMapping("/{cuenta_id}")
    public ResponseEntity<BaseResponseSimpleDTO> obtenerCuenta(
            @Valid @PathVariable("cuenta_id") Long cuentaId
    ){
        return new ResponseEntity<>(cuentaService.obtenerCuenta(cuentaId), HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<BaseResponseDTO> obtenerTodasLasCuentas(){
        return new ResponseEntity<>(cuentaService.obtenerTodasLasCuentas(), HttpStatus.OK);
    }

    @PutMapping("/{cuenta_id}")
    public ResponseEntity<BaseResponseDTO> actualizarCuenta(
            @Valid @PathVariable("cuenta_id") Long cuentaId,
            @Valid @RequestBody CuentaDTO  nuevo_cuenta
    ) {
        return new ResponseEntity<>(cuentaService.actualizarCuenta(nuevo_cuenta, cuentaId), HttpStatus.OK);
    }

    @DeleteMapping("/{cuenta_id}")
    public ResponseEntity<BaseResponseDTO> eliminarCuenta(
            @Valid @PathVariable("cuenta_id") Long cuentaId
    ){
        return new ResponseEntity<>(cuentaService.eliminarCuenta(cuentaId), HttpStatus.OK);
    }
}
