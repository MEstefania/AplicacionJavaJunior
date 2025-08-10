package org.sofkajavajunior.controllers;


import org.sofkajavajunior.dto.ClienteDTO;
import org.sofkajavajunior.dto.respuestaBase.BaseResponseDTO;
import org.sofkajavajunior.dto.respuestaBase.BaseResponseSimpleDTO;
import org.sofkajavajunior.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<BaseResponseDTO> crearCliente(
            @Valid @RequestBody ClienteDTO  nuevo_cliente
    ) {
        return new ResponseEntity<>(clienteService.crearCliente(nuevo_cliente), HttpStatus.OK);
    }
    @GetMapping("/{cliente_id}")
    public ResponseEntity<BaseResponseSimpleDTO> obtenerCliente(
            @Valid @PathVariable("cliente_id") Long clienteId
    ){
        return new ResponseEntity<>(clienteService.obtenerCliente(clienteId), HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<BaseResponseDTO> obtenerTodosLosClientes(){
        return new ResponseEntity<>(clienteService.obtenerTodosLosClientes(), HttpStatus.OK);
    }

    @PutMapping("/{cliente_id}")
    public ResponseEntity<BaseResponseDTO> actualizarCliente(
            @Valid @PathVariable("cliente_id") Long clienteId,
            @Valid @RequestBody ClienteDTO  nuevo_cliente
    ) {
        return new ResponseEntity<>(clienteService.actualizarCliente(nuevo_cliente, clienteId), HttpStatus.OK);
    }

    @DeleteMapping("/{cliente_id}")
    public ResponseEntity<BaseResponseDTO> eliminarCliente(
            @Valid @PathVariable("cliente_id") Long clienteId
    ){
        return new ResponseEntity<>(clienteService.eliminarCliente(clienteId), HttpStatus.OK);
    }

}
