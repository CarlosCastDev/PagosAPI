package com.bankXx.pagosAPI.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankXx.pagosAPI.entity.Pago;
import com.bankXx.pagosAPI.repository.PagoRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PagoService {

    private final PagoRepository pagoRepository;
    
    @Autowired
    private AmqpTemplate rabbitTemplate;

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
            
            String mensaje = "El estatus del pago con ID " + pago.getId() + " ha cambiado a " + nuevoEstatus;
            rabbitTemplate.convertAndSend("cambio-estatus-pago", mensaje);
            log.info("Notificación de cambio de estatus enviada a RabbitMQ: " + mensaje);
            
            return "Estatus del pago actualizado correctamente";
        } else {
            return "Pago no encontrado";
        }
    }
}

