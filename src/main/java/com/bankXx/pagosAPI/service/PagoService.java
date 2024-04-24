package com.bankXx.pagosAPI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankXx.pagosAPI.entity.Pago;
import com.bankXx.pagosAPI.repository.PagoRepository;

@Service
public class PagoService {

    private final PagoRepository pagoRepository;

    @Autowired
    public PagoService(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }

    public Pago crearPago(Pago pago) {
        // Aquí podrías realizar validaciones u operaciones adicionales antes de guardar el pago en la base de datos
        return pagoRepository.save(pago);
    }

    public String verificarEstatusPago(Long id) {
        Pago pago = pagoRepository.findById(id).orElse(null);
        if (pago != null) {
            return pago.getEstatus();
        } else {
            return "Pago no encontrado";
        }
    }

    public String cambiarEstatusPago(Long id, String nuevoEstatus) {
        Pago pago = pagoRepository.findById(id).orElse(null);
        if (pago != null) {
            pago.setEstatus(nuevoEstatus);
            pagoRepository.save(pago);
            return "Estatus del pago actualizado correctamente";
        } else {
            return "Pago no encontrado";
        }
    }
}

