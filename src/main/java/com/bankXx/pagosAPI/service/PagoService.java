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
    
    
    private AmqpTemplate rabbitTemplate;

    @Autowired
    public PagoService(PagoRepository pagoRepository, AmqpTemplate rabbitTemplate) {
        this.pagoRepository = pagoRepository;
        this.rabbitTemplate = rabbitTemplate;
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
			String estatusAnterior = pago.getEstatus();
			pago.setEstatus(nuevoEstatus);
			Pago pagoActualizado = pagoRepository.saveAndFlush(pago);

			if (!estatusAnterior.equals(pagoActualizado.getEstatus())) {

				String mensaje = new StringBuilder("El estatus del pago con ID ").append(pago.getId())
						.append(" ha cambiado a ").append(nuevoEstatus).toString();
				
				rabbitTemplate.convertAndSend("cambio-estatus-pago", mensaje);
				
				log.info("********************************************");
		    	log.info("********************************************");
		    	log.info("********************************************");
				log.info("Notificación de cambio de estatus ENVIADA a RabbitMQ: " + mensaje);
				log.info("********************************************");
		    	log.info("********************************************");
		    	log.info("********************************************");
			}

			return "Estatus del pago actualizado correctamente";
		} else {
			return "Pago no encontrado";
		}
    }
}

