package org.sofkajavajunior.controllers;

import org.sofkajavajunior.dto.ClienteDTO;
import org.sofkajavajunior.dto.respuestaBase.BaseRespondeDTO;
import org.sofkajavajunior.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @PostMapping("/")
    public ResponseEntity<BaseRespondeDTO> crearCliente(
            @Valid @RequestBody ClienteDTO  nuevo_cliente
    ) {
        return new ResponseEntity<>(clienteService.crearCliente(nuevo_cliente), HttpStatus.OK);
    }
    @GetMapping("/{cliente_id}")
    public ResponseEntity<BaseRespondeDTO> obtenerCliente(
            @Valid @PathVariable("cliente_id") Long clienteId
    ){
        return new ResponseEntity<>(clienteService.obtenerCliente(clienteId), HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<BaseRespondeDTO> obtenerTodosLosClientes(
            @Valid @PathVariable("cliente_id") Long clienteId
    ){
        return new ResponseEntity<>(clienteService.obtenerTodosLosClientes(), HttpStatus.OK);
    }

    @PutMapping("/{cliente_id}")
    public ResponseEntity<BaseRespondeDTO> actualizarCliente(
            @Valid @PathVariable("cliente_id") Long clienteId,
            @Valid @RequestBody ClienteDTO  nuevo_cliente
    ) {
        return new ResponseEntity<>(clienteService.actualizarCliente(nuevo_cliente, clienteId), HttpStatus.OK);
    }

    @DeleteMapping("/{cliente_id}")
    public ResponseEntity<BaseRespondeDTO> eliminarCliente(
            @Valid @PathVariable("cliente_id") Long clienteId
    ){
        return new ResponseEntity<>(clienteService.eliminarCliente(clienteId), HttpStatus.OK);
    }

}
