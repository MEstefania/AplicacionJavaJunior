package org.sofkajavajunior.controllers;

import org.sofkajavajunior.dto.respuestaBase.BaseResponseDTO;
import org.sofkajavajunior.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;

@RestController
@RequestMapping("/reportes")
public class ReportsController {
    @Autowired
    private ReporteService reporteService;

    @GetMapping("/{cliente_id}")
    public ResponseEntity<BaseResponseDTO> obtenerCliente(
            @Valid @PathVariable("cliente_id") Long clienteId,
            @NotNull @RequestParam(value = "fechaInicio") Date fechaInicio,
            @NotNull @RequestParam(value = "fechaFin") Date fechaFin
    ){
        return new ResponseEntity<>(reporteService.obtenerTodasLasCuentasSegunClienteYFecha(fechaInicio, fechaFin,clienteId), HttpStatus.OK);
    }
}
