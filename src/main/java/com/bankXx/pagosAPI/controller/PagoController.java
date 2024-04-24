package com.bankXx.pagosAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankXx.pagosAPI.entity.Pago;
import com.bankXx.pagosAPI.service.PagoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/pagos")
public class PagoController {

    private final PagoService pagoService;

    @Autowired
    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @Operation(summary = "Crear un nuevo pago")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Pago creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping
    public ResponseEntity<Pago> crearPago(@RequestBody Pago pago) {
        Pago nuevoPago = pagoService.crearPago(pago);
        return new ResponseEntity<>(nuevoPago, HttpStatus.CREATED);
    }

    @Operation(summary = "verificar estatus de un pago")
    @GetMapping("/{id}")
    public ResponseEntity<String> verificarEstatusPago(@PathVariable Long id) {
        String estatus = pagoService.verificarEstatusPago(id);
        return ResponseEntity.ok(estatus);
    }

    @Operation(summary = "modificar estatus de un pago")
    @PutMapping("/{id}/estatus")
    public ResponseEntity<String> cambiarEstatusPago(@PathVariable Long id, @RequestParam String nuevoEstatus) {
        String mensaje = pagoService.cambiarEstatusPago(id, nuevoEstatus);
        return ResponseEntity.ok(mensaje);
    }
}

