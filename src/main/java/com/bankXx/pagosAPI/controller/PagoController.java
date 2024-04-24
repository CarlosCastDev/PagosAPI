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

@RestController
@RequestMapping("/pagos")
public class PagoController {

    private final PagoService pagoService;

    @Autowired
    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @PostMapping
    public ResponseEntity<Pago> crearPago(@RequestBody Pago pago) {
        Pago nuevoPago = pagoService.crearPago(pago);
        return new ResponseEntity<>(nuevoPago, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> verificarEstatusPago(@PathVariable Long id) {
        String estatus = pagoService.verificarEstatusPago(id);
        return ResponseEntity.ok(estatus);
    }

    @PutMapping("/{id}/estatus")
    public ResponseEntity<String> cambiarEstatusPago(@PathVariable Long id, @RequestParam String nuevoEstatus) {
        String mensaje = pagoService.cambiarEstatusPago(id, nuevoEstatus);
        return ResponseEntity.ok(mensaje);
    }
}

